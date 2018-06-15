package com.zhuoyue.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @author gzd
 * @date 2017/10/27 10:34
 * @desc 未登录异常
 */
public class NotLoginException extends RuntimeException {

    /**
     * 登录后的回调地址
     */
    private String backUrl = StringUtils.EMPTY;

    public NotLoginException() {
        super("请先登录");
    }

    public NotLoginException(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getBackUrl() {
        return backUrl;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
