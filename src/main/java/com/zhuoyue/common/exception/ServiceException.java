package com.zhuoyue.common.exception;

/**
 * @author gzd
 * @date 2017/10/27 10:35
 * @desc 业务异常. 与 ServiceMustHandleException 不同,
 * 此异常会跳到一个指定的错误页面而丢失原先的用户操作
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
        super("业务异常");
    }

    public ServiceException(String msg) {
        super(msg);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
