package com.zhuoyue.vo;

public class ObjVo {

    public static final String OBJ = "obj";
    public static final String RBG = "txt";
    public static final String JPG = "jpg";

    private String id;
    /**
     * 族名称
     */
    private String family;
    private String catagoryName;
    private String objName;
    private String objFilePath;
    private String jpgFilePath;
    private String rbg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjFilePath() {
        return objFilePath;
    }

    public void setObjFilePath(String objFilePath) {
        this.objFilePath = objFilePath;
    }

    public String getJpgFilePath() {
        return jpgFilePath;
    }

    public void setJpgFilePath(String jpgFilePath) {
        this.jpgFilePath = jpgFilePath;
    }

    public String getRbg() {
        return rbg;
    }

    public void setRbg(String rbg) {
        this.rbg = rbg;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }
}
