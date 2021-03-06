package org.lht.boot.security.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author LiHaitao
 * @description SecException: Security自定义异常
 * @date 2020/3/23 17:00
 **/
public class SecException extends AuthenticationException {


    public SecException(String message) {
        super(message);
    }
}
