package com.zhuoyue.common.web.config.converter;

import com.zhuoyue.common.date.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * @author gzd
 * @date 2017/10/27 9:19
 * @desc 字符串转换为日期对象. 匹配多种形式! 前台传过来的不管是 yyyy-MM-dd HH:mm:ss 还是 yyyy-MM-dd 都能转换成 Date 对象.
 * 全部的格式参见 DateFormatType, 当没有匹配时返回 null 而不是抛出异常
 * 此 convert 用于替代 org.springframework.format.annotation.DateTimeFormat 注解
 */
public class String2DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        return DateUtil.parseSpecified(s);
    }
}
