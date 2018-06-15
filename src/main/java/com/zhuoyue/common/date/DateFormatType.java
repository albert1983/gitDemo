package com.zhuoyue.common.date;

/**
 * @author gzd
 * @date 2017/10/21 22:38
 * @desc 枚举类:日期的格式化类型
 */
public enum DateFormatType {

    /**
     * 日期:年月日时分秒
     */
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),

    /**
     * 日期:年月日时分
     */
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),

    /**
     * 日期:年月日
     */
    YYYY_MM_DD("yyyy-MM-dd"),

    /**
     * 日期:年月日时分吗,秒
     */
    YYYYMMDDHHMMSS("yyyyMMddHHmmss"),

    /**
     * 日期年月日
     */
    YYYYMMDD("yyyyMMdd"),

    /**
     * 日期:年月日
     */
    YYMMDD("yyMMdd"),

    HH_MM_SS("HH:mm:ss"),

    HH_MM("HH:mm"),

    /**
     * yyyy/MM/dd
     */
    DIAGONALS_SPLIT("yyyy/MM/dd"),

    /**
     * MM/dd/yyyy HH:mm:ss
     */
    USA_COMPLETE("MM/dd/yyyy HH:mm:ss"),

    /**
     * yyyy年MM月dd日 HH时mm分ss秒
     */
    CN_YYYY_MM_DD_HH_MM_SS("yyyy年MM月dd日 HH时mm分ss秒"),

    /**
     * yyyy年MM月dd日 HH点
     */
    CN_YYYY_MM_DD_HH("yyyy年MM月dd日 HH点"),

    /**
     * yyyy年MM月dd日
     */
    CN_YYYY_MM_DD("yyyy年MM月dd日");

    private String value;

    DateFormatType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
