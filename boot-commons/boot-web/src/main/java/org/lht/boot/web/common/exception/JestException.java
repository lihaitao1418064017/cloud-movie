package org.lht.boot.web.common.exception;

/**
 * @author LiHaitao
 * @description JestException:
 * @date 2020/1/2 19:52
 **/
public class JestException extends CommonException {

    public JestException(String message) {
        super(message);
    }

    public JestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
