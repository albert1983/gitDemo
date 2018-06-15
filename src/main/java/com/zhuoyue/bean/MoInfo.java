package com.zhuoyue.bean;

import java.io.Serializable;

/**
 * 模型信息
 *
 * @author
 */
public class MoInfo implements Serializable {


    private static final long serialVersionUID = 3208164596506766547L;

    /**
     * 模型ID
     */
    private String id;
    /**
     * 模型名称
     */
    private String name;
    /**
     * 模型描述
     */
    private String des;


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
}