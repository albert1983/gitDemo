package com.zhuoyue.bean;

import java.io.Serializable;

/**
 * @author
 */
public class MoStempInfo implements Serializable {


    private static final long serialVersionUID = -8107552461869057245L;


    private String id;
    /**
     * 阶段名称
     */
    private String stempName;
    /**
     * 人员ID
     */
    private String userId;
    /**
     * 阶段状态
     */
    private String stempState;
    /**
     * 时间
     */
    private String createTime;
    /**
     * 模型ID
     */
    private String modeId;
    /**
     * 模型管理类型
     */
    private String managerType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStempName() {
        return stempName;
    }

    public void setStempName(String stempName) {
        this.stempName = stempName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStempState() {
        return stempState;
    }

    public void setStempState(String stempState) {
        this.stempState = stempState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModeId() {
        return modeId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    public String getManagerType() {
        return managerType;
    }

    public void setManagerType(String managerType) {
        this.managerType = managerType;
    }
}