package org.lht.boot.security.common.util;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @description: JWT-Token工具类
 * @author: Lihaitao
 * @date: 2020/3/25 10:21
 **/
@Component
public class JwtTokenUtil {

    private final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final String AUTHORITIES_KEY = "auth";

    /**
     * 签名密钥
     */
    private String secretKey;
    /**
     * 失效日期
     */
    private long tokenValidityInMilliseconds;

    /**
     * 记住我失效日期
     */
    private long tokenValidityInMillisecondsForRememberMe;

    @PostConstruct
    public void init() {
        this.secretKey = "lht";
        int secondIn1day = 1000 * 60 * 60 * 24;
        this.tokenValidityInMilliseconds = secondIn1day * 2L;
        this.tokenValidityInMillisecondsForRememberMe = secondIn1day * 7L;
    }

    /**
     * 过期警告时间
     */
    private final static long EXPIRATIONTIME = 432_000_000;

    //创建Token
    public String createToken(Authentication authentication, Boolean rememberMe) {
        //获取用户的权限字符串，如 USER,ADMIN
        String authorities = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        //存放过期时间
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        } else {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        }
        //创建Token令牌
        return Jwts.builder()
                //设置面向用户
                .setSubject(authentication.getName())
                //添加权限属性
                .claim(AUTHORITIES_KEY, authorities)
                //设置失效时间
                .setExpiration(validity)
                //生成签名
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    /**
     * 获取用户权限
     *
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        System.out.println("token:" + token);
        //解析Token的payload
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                //获取用户权限字符串
                Arrays.stream(claims
                        .get(AUTHORITIES_KEY)
                        .toString()
                        .split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * 验证Token是否正确
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            //通过密钥验证Token
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
            //签名异常
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
            //JWT格式错误
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
            //JWT过期
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
            //不支持该JWT
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
            //不支持该JWT
        } catch (IllegalArgumentException e) {                              //参数错误异常
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }
}
