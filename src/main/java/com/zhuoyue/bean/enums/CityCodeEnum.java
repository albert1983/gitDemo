package com.zhuoyue.bean.enums;

/**
 * @author gzd
 * @date 2017/11/2 15:15
 * @desc input the desc
 */
public enum CityCodeEnum {

    BEIJING("001", "北京"),
    SHANGHAI("002", "上海");


    /**
     * 存储在 db 中的具体的值, 序列化到前台时也使用此值
     */
    private String code;

    private String value;


    CityCodeEnum(String cityCode, String cityName) {
        this.code = cityCode;
        this.value = cityName;
    }

    public String getCode() {
        return code;
    }

    public CityCodeEnum setCode(String code) {
        this.code = code;
        return this;
    }

    public String getValue() {
        return value;
    }

    public CityCodeEnum setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * 根据传入的字符串返回对应的枚举值, 若没有则返回 null
     *
     * @param value
     * @return
     */
    public static CityCodeEnum toEnum(String value) {
        if (value == null) return null;

        for (CityCodeEnum cityCodeEnum : values()) {
            if (cityCodeEnum.name().equalsIgnoreCase(value.trim()))
                return cityCodeEnum;
        }
        return null;
    }
}
