package com.zhuoyue.bean;

import java.io.Serializable;

/**
 * @author
 */
public class MoManagerType implements Serializable {


    private static final long serialVersionUID = 4561498731581258907L;
    private String id;
    /**
     * 模型管理名称
     */
    private String name;
    /**
     * 描述
     */
    private String des;
    /**
     * 图片
     */
    private String iconUrl;
    /**
     * 类型
     */
    private String type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}