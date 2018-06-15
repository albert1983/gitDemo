package com.zhuoyue.bean;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 文件中元素属性
 *
 * @author
 */
public class MoCompElement implements Serializable {


    private static final long serialVersionUID = -3869796691450871204L;

    /**
     * 元素ID
     */
    private String id;
    /**
     * 族类型文件ID
     */
    private String famplyTypeId;
    /**
     * 模型ID
     */
    private String modeId;

    private String elementName;
    /**
     * 元素属性
     */
    private String properties;

    private JSONObject propertiesObject;

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public String toString() {
        return "MoCompElement{" +
                "id='" + id + '\'' +
                ", famplyTypeId='" + famplyTypeId + '\'' +
                ", modeId='" + modeId + '\'' +
                ", elementName='" + elementName + '\'' +
                ", properties='" + properties + '\'' +
                ", propertiesObject=" + propertiesObject +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFamplyTypeId() {
        return famplyTypeId;
    }

    public void setFamplyTypeId(String famplyTypeId) {
        this.famplyTypeId = famplyTypeId;
    }

    public String getModeId() {
        return modeId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public JSONObject getPropertiesObject() {
        return propertiesObject;
    }

    public void setPropertiesObject(JSONObject propertiesObject) {
        this.propertiesObject = propertiesObject;
    }

}