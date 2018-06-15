package com.zhuoyue.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 类别
 *
 * @author 14258
 */
public class Category implements Serializable {

    private String categoryId;
    private String categoryName;
    private List<Family> families;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Family> getFamilies() {
        return families;
    }

    public void setFamilies(List<Family> families) {
        this.families = families;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", families=" + families +
                '}';
    }
}
