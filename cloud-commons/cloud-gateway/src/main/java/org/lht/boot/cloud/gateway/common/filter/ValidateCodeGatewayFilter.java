/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lht.boot.cloud.gateway.common.filter;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.cache.redis.RedisUtil;
import org.lht.boot.cloud.gateway.common.constant.CommonConstant;
import org.lht.boot.cloud.gateway.common.exception.ValidateCodeException;
import org.lht.boot.cloud.gateway.common.util.WebUtils;
import org.lht.boot.lang.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


/**
 * @description: 验证码处理
 * @author: LiHaitao
 * @date: 2020/7/17 17:00
 */
@Slf4j
@Component
@AllArgsConstructor
public class ValidateCodeGatewayFilter extends AbstractGatewayFilterFactory {
    private final ObjectMapper objectMapper;

    private static final String clientId = null;

    @Autowired
    private RedisUtil<String, String> redisUtil;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath()
                    , "/login")) {
                return chain.filter(exchange);
            }

            // 刷新token，直接向下执行
            String grantType = request.getQueryParams().getFirst("grant_type");
            if (StrUtil.equals("refresh_token", grantType)) {
                return chain.filter(exchange);
            }

            // 直接向下执行
            try {
                String[] clientInfos = WebUtils.getClientId(request);
                if (clientId != null && clientId.equals(clientInfos[0])) {
                    return chain.filter(exchange);
                }

                //校验验证码
                checkCode(request);
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.PRECONDITION_REQUIRED);
                try {
                    return response.writeWith(Mono.just(response.bufferFactory()
                            .wrap(objectMapper.writeValueAsBytes(
                                    R.error(e.getMessage())))));
                } catch (JsonProcessingException e1) {
                    log.error("对象输出异常", e1);
                }
            }

            return chain.filter(exchange);
        };
    }

    /**
     * 检查code
     *
     * @param request
     */
    @SneakyThrows
    private void checkCode(ServerHttpRequest request) {
        String code = request.getQueryParams().getFirst("code");

        if (StrUtil.isBlank(code)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        String randomStr = request.getQueryParams().getFirst("randomStr");
        if (StrUtil.isBlank(randomStr)) {
            randomStr = request.getQueryParams().getFirst("mobile");
        }

        String key = CommonConstant.DEFAULT_CODE_KEY + randomStr;
        if (!redisUtil.hasKey(key)) {
            throw new ValidateCodeException("验证码不合法");
        }

        Object codeObj = redisUtil.get(key);

        if (codeObj == null) {
            throw new ValidateCodeException("验证码不合法");
        }

        String saveCode = codeObj.toString();
        if (StrUtil.isBlank(saveCode)) {
            redisUtil.delete(key);
            throw new ValidateCodeException("验证码不合法");
        }

        if (!StrUtil.equals(saveCode, code)) {
            redisUtil.delete(key);
            throw new ValidateCodeException("验证码不合法");
        }

        redisUtil.delete(key);
    }
}
