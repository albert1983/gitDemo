package com.zhuoyue.common.web;


import com.zhuoyue.common.dto.JsonResult;

import static io.advantageous.boon.core.Maps.map;

/**
 * @author gzd
 * @date 2017/10/8 22:38
 * @desc controller的基类
 */
public class BaseController {

    protected JsonResult ok() {
        return new JsonResult(true);
    }

    protected JsonResult ok(String msg) {
        return new JsonResult(true, msg);
    }

    protected JsonResult ok(String msg, Object object) {
        return new JsonResult(true, msg, object);
    }

    protected JsonResult ok(String msg, String k1, Object v1) {
        return new JsonResult(true, msg, map(k1, v1));
    }

    protected JsonResult ok(String msg, String k1, Object v1, String k2, Object v2) {
        return new JsonResult(true, msg, map(k1, v1, k2, v2));
    }

    protected JsonResult ok(String msg, String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        return new JsonResult(true, msg, map(k1, v1, k2, v2, k3, v3));
    }

    protected JsonResult ok(String msg, String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4) {
        return new JsonResult(true, msg, map(k1, v1, k2, v2, k3, v3, k4, v4));
    }

    protected JsonResult ok(String msg, String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5) {
        return new JsonResult(true, msg, map(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5));
    }

    protected JsonResult okOrFailRawData(String jsonData, String errMsg) {
        if (jsonData != null) {
            return new JsonResult(true).setData(jsonData);
        } else {
            return new JsonResult(false, errMsg);
        }
    }

    protected JsonResult okOrFailRawData(String jsonData, String successMsg, String errMsg) {
        if (jsonData != null) {
            return new JsonResult(true, successMsg).setData(jsonData);
        } else {
            return new JsonResult(false, errMsg);
        }
    }

    protected JsonResult fail() {
        return new JsonResult(false);
    }

    protected JsonResult fail(String msg) {
        return new JsonResult(false, msg);
    }

    protected JsonResult fail(String msg, Object object) {
        return new JsonResult(false, msg, object);
    }

    protected JsonResult fail(String msg, String k1, Object v1) {
        return new JsonResult(false, msg, map(k1, v1));
    }

    protected JsonResult fail(String msg, String k1, Object v1, String k2, Object v2) {
        return new JsonResult(false, msg, map(k1, v1, k2, v2));
    }

    protected JsonResult fail(String msg, String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        return new JsonResult(false, msg, map(k1, v1, k2, v2, k3, v3));
    }

}
