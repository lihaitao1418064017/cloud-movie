package org.lht.boot.security.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @description 验证码校验异常
 * @date 2020-03-24
 * @author lht
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 5022575393500654458L;

    public ValidateCodeException(String message) {
        super(message);
    }
}
