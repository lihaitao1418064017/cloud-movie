package org.lht.boot.lang.util;

import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author LiHaitao
 * @description CaptchaUtils:验证码工具类
 * @date 2020/3/5 9:05
 **/
public class CaptchaUtils extends cn.hutool.captcha.CaptchaUtil {

    /**
     * 将验证码写入浏览器
     *
     * @param response  http请求的response
     * @param width     宽
     * @param height    高
     * @param codeCount 验证码长度
     * @param lineCount 干扰线条数
     * @throws IOException
     */
    public String writeLineCaptcha(HttpServletResponse response, int width, int height, int codeCount, int lineCount) {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtils.createLineCaptcha(width, height, codeCount, lineCount);
        String code = lineCaptcha.getCode();
        //输出浏览器
        try (OutputStream out = response.getOutputStream()) {
            lineCaptcha.write(out);
            return code;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    /**
     * 将验证码写入浏览器
     *
     * @param response    http请求的response
     * @param width       宽
     * @param height      高
     * @param codeCount   验证码长度
     * @param circleCount 干扰圆圈数数
     * @throws IOException
     */
    public String writeCircleCaptcha(HttpServletResponse response, int width, int height, int codeCount, int circleCount) throws IOException {
        CircleCaptcha circleCaptcha = CaptchaUtils.createCircleCaptcha(width, height, codeCount, circleCount);
        String code = circleCaptcha.getCode();

        try (OutputStream out = response.getOutputStream()) {
            circleCaptcha.write(out);
            return code;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将验证码写入浏览器
     *
     * @param response  http请求的response
     * @param width     宽
     * @param height    高
     * @param codeCount 验证码长度
     * @param thickness 厚度
     * @throws IOException
     */
    public String writeShearCaptcha(HttpServletResponse response, int width, int height, int codeCount, int thickness) throws IOException {
        ShearCaptcha shearCaptcha = CaptchaUtils.createShearCaptcha(width, height, codeCount, thickness);
        String code = shearCaptcha.getCode();
        try (OutputStream out = response.getOutputStream()) {
            shearCaptcha.write(out);
            return code;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
