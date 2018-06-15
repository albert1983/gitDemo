package com.zhuoyue.vo;

import java.util.List;

public class Tree {


    public static final String FLOOR = "0";
    public static final String SPECIALTY = "1";
    public static final String CATEGORY = "2";
    public static final String FAMILY = "3";
    public static final String FAMILYTYPE = "4";


    private String id;
    private String name;
    private List<Tree> children;
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

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
