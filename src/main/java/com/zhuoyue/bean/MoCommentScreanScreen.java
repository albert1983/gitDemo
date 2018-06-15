package com.zhuoyue.bean;

import java.io.Serializable;

/**
 * @author
 */
public class MoCommentScreanScreen implements Serializable {


    private static final long serialVersionUID = 1056030891322733474L;


    private String id;

    /**
     * 图片路径
     */
    private String filePath;
    /**
     * 名称
     */
    private String name;
    /**
     * 模型文件ID
     */
    private String typeFileId;
    /**
     * 描述
     */
    private String des;

    private String modelRotation;

    private String cameraSize;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeFileId() {
        return typeFileId;
    }

    public void setTypeFileId(String typeFileId) {
        this.typeFileId = typeFileId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getModelRotation() {
        return modelRotation;
    }

    public void setModelRotation(String modelRotation) {
        this.modelRotation = modelRotation;
    }

    public String getCameraSize() {
        return cameraSize;
    }

    public void setCameraSize(String cameraSize) {
        this.cameraSize = cameraSize;
    }
}