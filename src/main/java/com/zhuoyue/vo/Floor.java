package com.zhuoyue.vo;


import java.io.Serializable;
import java.util.List;

/**
 * 楼层
 *
 * @author 14258
 */
public class Floor implements Serializable {


    private static final long serialVersionUID = 3984375370821948422L;
    private String floor;
    private List<Category> categories;

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
