package com.zhuoyue.vo;

import java.io.Serializable;
import java.util.List;

public class Specialty implements Serializable {


    private String specialty;
    private List<Category> categories;

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}