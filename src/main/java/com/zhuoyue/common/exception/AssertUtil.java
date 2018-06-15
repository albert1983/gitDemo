package com.zhuoyue.common.exception;


import com.zhuoyue.common.util.ObjectUtil;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author gzd
 * @date 2017/10/27 10:43
 * @desc 类似于断言的抛出异常
 */
public class AssertUtil {

    /**
     * 对象为空则抛出异常
     *
     * @param obj
     * @param msg
     */
    public static void assertNil(Object obj, String msg) {
        assertException(obj == null, msg);
    }

    /**
     * 对象不为空则抛出异常
     *
     * @param obj
     * @param msg
     */
    public static void assertNotNil(Object obj, String msg) {
        assertException(obj != null, msg);
    }

    /**
     * 数值为空或小于 1 则抛出异常
     *
     * @param obj
     * @param msg
     */
    public static void assertZero(Number obj, String msg) {
        assertException(ObjectUtil.lessZero(obj), msg);
    }

    /**
     * 对象为 null, 空白符, "null" 时则抛出异常
     *
     * @param obj
     * @param msg
     */
    public static void assertNilStr(Object obj, String msg) {
        assertException(obj == null || isBlank(obj.toString()), msg);
    }

    /**
     * 抛出业务异常
     *
     * @param flag
     * @param msg
     */
    public static void assertException(Boolean flag, String msg) {
        if (flag != null && flag) throw new ServiceException(msg);
    }
}
