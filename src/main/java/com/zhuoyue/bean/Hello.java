package com.zhuoyue.bean;


import com.zhuoyue.bean.enums.CityCodeEnum;

public class Hello {
    /**
     * null
     */
    private String id;

    /**
     * null
     */
    private String userName;

    /**
     * null
     */
    private Boolean deleted;

    private CityCodeEnum cityCode;


    public CityCodeEnum getCityCode() {
        return cityCode;
    }

    public Hello setCityCode(CityCodeEnum cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public String getId() {
        return id;
    }

    public Hello setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Hello setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Hello setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}