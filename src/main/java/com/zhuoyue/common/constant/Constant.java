package com.zhuoyue.common.constant;


/**
 * @author gzd
 * @date 2017/11/6 10:47
 * @desc 常量类
 */
public class Constant {

    /**
     * 分页查询的每页默认条数
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 分页查询默认的页数
     */
    public static final int DEFAULT_PAGE_NO = 1;


    public  enum Platform {
        PC("0", ""),
        IOS("1", "ios端下载"),
        Andriod("2", "android端下载");


        private String code;

        private String value;


        Platform(String code, String value) {
            this.code = code;
            this.value = value;
        }


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}
