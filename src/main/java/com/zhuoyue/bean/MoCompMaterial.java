package com.zhuoyue.bean;

import java.io.Serializable;

/**
 * 文件贴图
 *
 * @author
 */
public class MoCompMaterial implements Serializable {

    private static final long serialVersionUID = 540935626930659275L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 图像材质
     */
    private String rbg;
    /**
     * OBJ ID
     */
    private String objId;
    /**
     * 贴图材质路径
     */
    private String filePath;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRbg() {
        return rbg;
    }

    public void setRbg(String rbg) {
        this.rbg = rbg;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}