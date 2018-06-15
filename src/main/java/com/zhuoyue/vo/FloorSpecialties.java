package com.zhuoyue.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 楼层-专业-分类
 *
 * @author 14258
 */
public class FloorSpecialties implements Serializable {


    private static final long serialVersionUID = 7099900316595446338L;

    private String floor;
    private List<Specialty> specialties;


    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }
}
