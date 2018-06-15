package com.zhuoyue.bean;

import java.io.Serializable;

/**
 * @author 14258
 */
public class MoCompHierarchy implements Serializable {

    private static final long serialVersionUID = -3104828575833120942L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 模型ID
     */
    private String modeId;
    /**
     * 楼层
     */
    private String floor;
    /**
     * 类别名称
     */
    private String specialty;
    /**
     * 专业名称
     */
    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModeId() {
        return modeId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "MoCompHierarchy{" +
                "id='" + id + '\'' +
                ", modeId='" + modeId + '\'' +
                ", floor='" + floor + '\'' +
                ", specialty='" + specialty + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
