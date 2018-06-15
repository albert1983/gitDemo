package com.zhuoyue.common.util;

import com.zhuoyue.common.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author gzd
 * @date 2017/11/9 17:30
 * @desc 反射相关方法
 */
public class ReflectUtil {

    /**
     * 调用对象的属性对应的 get 方法
     *
     * @param data  对象
     * @param field 属性名
     * @return 如果属性是枚举, 则调用枚举的 getValue 方法, 如果是日期, 则格式化, 否则返回此属性值, 异常则返回 空字符串
     */
    public static String handleField(Object data, String field) {
        if (data == null || isBlank(field)) return StringUtils.EMPTY;

        Object value = handleMethod(data, fieldToMethod(field));
        if (value == null) return StringUtils.EMPTY;

        // 如果是枚举, 则调用其 getValue 方法, getValue 没有值则使用枚举的 name
        if (value.getClass().isEnum()) {
            Object enumValue = handleMethod(value, "getValue");
            return ObjectUtil.handleNil(enumValue != null ? enumValue : value.toString());
        }
        // 如果是日期, 则格式化
        if (value instanceof Date) {
            return ObjectUtil.handleNil(DateUtil.formatFull((Date) value));
        }
        return ObjectUtil.handleNil(value);
    }

    /**
     * 调用对象的公有方法(不需要传入参数的方法)
     *
     * @param data
     * @param method
     * @return
     */
    public static Object handleMethod(Object data, String method) {
        try {
            return MethodUtils.invokeMethod(data, method);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    /**
     * 属性转换成方法, 加上 get 并首字母大写
     *
     * @param field
     * @return
     */
    public static String fieldToMethod(String field) {
        if (isBlank(field)) return StringUtils.EMPTY;

        field = field.trim();
        return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }
}
