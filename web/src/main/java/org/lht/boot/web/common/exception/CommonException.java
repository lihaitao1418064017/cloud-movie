package org.lht.boot.web.common.exception;

/**
 * @author LiHaitao
 * @description CommonException:通用异常
 * @date 2020/1/2 19:49
 **/
public class CommonException extends RuntimeException {

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }


}
