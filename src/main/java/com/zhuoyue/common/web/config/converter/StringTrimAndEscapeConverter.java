package com.zhuoyue.common.web.config.converter;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.util.HtmlUtils;

/**
 * @author gzd
 * @date 2017/10/27 9:39
 * @desc 前台传来的参数, 去除左右空格, 进行转义(过滤XSS)
 */
public class StringTrimAndEscapeConverter implements Converter<String, String> {

    @Override
    public String convert(String s) {
        if (s == null) {
            return StringUtils.EMPTY;
        }
        return HtmlUtils.htmlEscape(s.trim(), CharEncoding.UTF_8);
    }
}
