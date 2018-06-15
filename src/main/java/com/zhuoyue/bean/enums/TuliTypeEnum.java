package com.zhuoyue.bean.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author gzd
 * @date 2017/11/9 14:13
 * @desc 图例类型的枚举类
 */
public enum TuliTypeEnum {

    FENGKOU("0001", "风口"),
    GUANDFENGJ("0002", "管道风机"),
    NUANTFENGGFAM("0003", "暖通风管阀门");


    /**
     * 存储在 db 中的具体的值, 序列化到前台时也使用此值
     */
    private String code;

    private String value;


    TuliTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public TuliTypeEnum setCode(String code) {
        this.code = code;
        return this;
    }

    public String getValue() {
        return value;
    }

    public TuliTypeEnum setValue(String value) {
        this.value = value;
        return this;
    }


    /**
     * 根据传入的字符串返回对应的枚举值, 若没有则返回 null
     *
     * @param code
     * @return
     */
    public static TuliTypeEnum toEnum(String code) {
        if (code == null) return null;
        for (TuliTypeEnum tuliTypeEnum : values()) {
            if (tuliTypeEnum.getCode().equalsIgnoreCase(code.trim()))
                return tuliTypeEnum;
        }
        return null;
    }

}
