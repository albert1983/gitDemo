package com.zhuoyue.bean;

import java.io.Serializable;

/**
 * 模型obj文件
 *
 * @author
 */
public class MoFamilyType implements Serializable {

    private static final long serialVersionUID = -6394033093345025204L;

    /**
     * 族类型ID
     */
    private String id;
    /**
     * 族名称
     */
    private String family;
    /**
     * 族类型名称
     */
    private String name;
    /**
     * 模型ID
     */
    private String modeId;
    /**
     * OBJ　文件路径
     */
    private String filePath;
    /**
     * 类别ID
     */
    private String categoryId;

    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModeId() {
        return modeId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}