package com.zhuoyue.vo;

import java.io.Serializable;

/**
 * @author 14258
 */
public class FamilyType implements Serializable {

    private static final long serialVersionUID = 6008877166965401975L;
    private String id;
    private String name;


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

    @Override
    public String toString() {
        return "FamilyType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
