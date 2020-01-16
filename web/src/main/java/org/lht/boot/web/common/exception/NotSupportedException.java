package org.lht.boot.web.common.exception;

/**
 * @author LiHaitao
 * @description NotSupportedException:
 * @date 2020/1/13 17:56
 **/
public class NotSupportedException extends CommonException {
    public NotSupportedException(String message) {
        super(message);
    }

    public NotSupportedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
