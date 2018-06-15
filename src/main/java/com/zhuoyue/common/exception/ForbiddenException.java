package com.zhuoyue.common.exception;

/**
 * @author gzd
 * @date 2017/10/27 10:31
 * @desc 没有访问权限异常
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("没有权限");
    }

    public ForbiddenException(String msg) {
        super(msg);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
