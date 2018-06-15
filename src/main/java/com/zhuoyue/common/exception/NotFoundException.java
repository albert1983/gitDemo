package com.zhuoyue.common.exception;

/**
 * @author gzd
 * @date 2017/10/27 10:32
 * @desc not found exception
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("404 Not Found");
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
