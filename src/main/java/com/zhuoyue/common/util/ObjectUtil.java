package com.zhuoyue.common.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author gzd
 * @date 2017/10/27 10:04
 * @desc object utils
 */
public class ObjectUtil {

    /**
     * 传入的数不为 null 且 大于 0 就返回 true
     *
     * @param obj
     * @return
     */
    public static boolean greaterZero(Number obj) {
        return !lessZero(obj);
    }

    /**
     * 传入的数为 null 或 小于等于 0 就返回 true
     */
    public static boolean lessZero(Number obj) {
        return obj == null || obj.doubleValue() <= 0;
    }

    /**
     * 生成不带 - 的 uuid
     *
     * @return
     */
    public static String uuid() {
        return (UUID.randomUUID().toString().replace("-", "")).toUpperCase();
    }

    /**
     * 严格的正则匹配,全部匹配则返回true
     *
     * @param param
     * @param regex
     * @return
     */
    public static boolean checkRegexWithStrict(String param, String regex) {
        return isNotBlank(param) && Pattern.compile(regex).matcher(param).matches();
    }

    /**
     * 不严格的正则匹配,只要匹配到就返回true
     *
     * @param param
     * @param regex
     * @return
     */
    public static boolean checkRegexWithRelax(String param, String regex) {
        return isNotBlank(param) && Pattern.compile(regex).matcher(param).find();
    }

    /**
     * 包含中文则返回true
     *
     * @param param
     * @return
     */
    public static boolean checkChinese(String param) {
        return checkRegexWithRelax(param, "[\\u4e00-\\u9fa5]");
    }

    /**
     * 获取图片后缀(包含点 .)
     *
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        return (isNotBlank(fileName) && fileName.contains("."))
                ? fileName.substring(fileName.lastIndexOf(".")) : StringUtils.EMPTY;
    }

    /**
     * 数值不在指定的数区间时(不包含边界)返回 true
     *
     * @param num
     * @param min
     * @param max
     * @return
     */
    public static boolean notBetween(Number num, Number min, Number max) {
        return !between(num, min, max);
    }

    /**
     * 数值在指定的数区间时(包含边界)返回 true
     *
     * @param num
     * @param min
     * @param max
     * @return
     */
    public static boolean between(Number num, Number min, Number max) {
        return num.doubleValue() >= min.doubleValue() && num.doubleValue() <= max.doubleValue();
    }

    /**
     * 用逗号把数组拼起来. 空数组则返回空字符串
     *
     * @param objArr
     * @return
     */
    public static String printArr(Object[] objArr) {
        if (ArrayUtils.isEmpty(objArr)) return StringUtils.EMPTY;

        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < objArr.length; i++) {
            sbd.append(objArr[i]);
            if (i + 1 != objArr.length) sbd.append(",");
        }
        return sbd.toString();
    }

    /**
     * 当值为 null, 空白符, "null" 时, 返回空字符串
     *
     * @param obj
     * @return
     */
    public static String handleNil(Object obj) {
        return handleNil(obj, StringUtils.EMPTY);
    }

    /**
     * 当值为 null, 空白符, "null" 时则返回指定的字符
     *
     * @param obj
     * @param defaultStr
     * @return
     */
    public static String handleNil(Object obj, String defaultStr) {
        return (obj == null || isBlank(obj.toString())) ? defaultStr : obj.toString().trim();
    }

    /**
     * 转换成 id=123&name=xyz&name=opq
     */
    public static String formatParam(Map<String, ?> params) {
        if (params == null || params.size() == 0) return StringUtils.EMPTY;

        StringBuilder sbd = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, ?> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value != null && isNotBlank(value.toString())) {
                if (value.getClass().isArray()) {
                    for (Object obj : (Object[]) value) {
                        if (i > 0) sbd.append("&");
                        i++;

                        sbd.append(entry.getKey()).append("=").append(obj);
                    }
                } else {
                    if (i > 0) sbd.append("&");
                    i++;
                    sbd.append(entry.getKey()).append("=").append(value);
                }
            }
        }
        return sbd.toString();
    }
}
