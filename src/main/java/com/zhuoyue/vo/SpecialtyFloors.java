package com.zhuoyue.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 专业-楼层-分类
 *
 * @author 14258
 */
public class SpecialtyFloors implements Serializable {


    private static final long serialVersionUID = -2177082960214194913L;
    private String specialty;
    private List<Floor> floors;

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }
}
