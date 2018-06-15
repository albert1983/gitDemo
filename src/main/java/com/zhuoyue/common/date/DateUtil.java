package com.zhuoyue.common.date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gzd
 * @date 2017/10/27 9:26
 * @desc 日期工具类
 */
public class DateUtil extends DateUtils {

    public static Date parseUnix(long unixStamp) {
        return new Date(unixStamp * 1000);
    }

    /**
     * 当前时间
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 获取当前时间日期的字符串
     */
    public static String now(DateFormatType dateFormatType) {
        return format(now(), dateFormatType);
    }

    /**
     * 返回 yyyy-MM-dd HH:mm:ss 格式的当前时间
     */
    public static String nowTime() {
        return now(DateFormatType.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 返回今天的 yyyy-MM-dd 格式
     */
    public static String nowDay() {
        return now(DateFormatType.YYYY_MM_DD);
    }

    public static String today() {
        return now(DateFormatType.YYMMDD);
    }

    /**
     * 格式化日期 yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        return format(date, DateFormatType.YYYY_MM_DD);
    }

    /**
     * 格式化时间 HH:mm:ss
     */
    public static String formatTime(Date date) {
        return format(date, DateFormatType.HH_MM_SS);
    }

    /**
     * 格式化日期和时间 yyyy-MM-dd HH:mm:ss
     */
    public static String formatFull(Date date) {
        return format(date, DateFormatType.YYYY_MM_DD_HH_MM_SS);
    }

    public static String format(Date date, DateFormatType dateFormatType) {
        if (date == null) return StringUtils.EMPTY;
        if (dateFormatType == null) return StringUtils.EMPTY;

        return DateFormatUtils.format(date, dateFormatType.getValue());
    }

    /**
     * 验证字符串是不是指定的某些样式, 若是, 则返回时间对象, 否则返回 null
     *
     * @param source 时间的字符串表现形式
     */
    public static Date parseSpecified(String source) {
        if (StringUtils.isBlank(source)) return null;
        for (DateFormatType formatType : DateFormatType.values()) {
            try {
                Date date = parse(source, formatType.getValue());
                if (date != null) return date;
            } catch (ParseException e) {
                // ignore
            }
        }
        return null;
    }

    /**
     * 解析日期. 默认的解析格式是 --> yyyy-MM-dd HH:mm:ss
     */
    public static Date parse(String source, String type) throws ParseException {
        if (StringUtils.isBlank(source)) return null;
        if (StringUtils.isBlank(type)) {
            type = DateFormatType.YYYY_MM_DD_HH_MM_SS.getValue();
        }
        return DateUtils.parseDate(source, type);
    }

    public static Date parse(String date, DateFormatType type) throws ParseException {
        return parse(date, type.getValue());
    }

    /**
     * 获取一个日期所在天的最开始的时间(00:00:00 00), 对日期段查询尤其有用
     */
    public static Date getDayStart(Date date) {
        if (date == null) return null;

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00 000");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        try {
            return sdf2.parse(sdf1.format(date));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取一个日期所在天的最晚的时间(23:59:59 999), 对日期查询尤其有用
     */
    public static Date getDayEnd(Date date) {
        if (date == null) return null;

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59 999");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        try {
            return sdf2.parse(sdf1.format(date));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 取得指定日期 N 天后的日期
     *
     * @param day 正数表示多少天后, 负数表示多少天前
     */
    public static Date addDays(Date date, int day) {
        return DateUtils.addDays(date, day);
    }

    /**
     * 取得指定日期 N 天后的日期
     *
     * @param month 正数表示多少月后, 负数表示多少月前
     */
    public static Date addMonths(Date date, int month) {
        return DateUtils.addMonths(date, month);
    }

}
