package com.zhuoyue.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author 
 */
 
@ApiModel( value="moLabelForm" ,description="moLabelForm" )
public class MoLabel implements Serializable {
	
	
	@ApiModelProperty(value="项目编号")
    private String projectCode;
	
	@ApiModelProperty(value="建筑编号")
    private String buildingCode;
 
	@ApiModelProperty(value="楼层编号")
    private String floorCode;
	
	@ApiModelProperty(value="专业编号")
    private String specialtyCode;
 
	@ApiModelProperty(value="构件编号")
    private String partCode;
 
	@ApiModelProperty(value="X座标")
    private String partPositionX;
 
	@ApiModelProperty(value="Y座标")
    private String partPositionY;
 
	@ApiModelProperty(value="Z座标")
    private String partPositionZ;

	@ApiModelProperty(value="内容")
    private String labelContent;

	@ApiModelProperty(value="标注日期" ,required=false,hidden =true)
    private String labelDate;

	@ApiModelProperty(value="音频路径" ,required=false,hidden =true)
    private String labelAudioPath;

	@ApiModelProperty(value="音频名称" ,required=false,hidden =true)
    private String labelAudioName;
	
	@ApiModelProperty(value="图像路径" ,required=false ,hidden =true )
    private String labeleImgPath;

	@ApiModelProperty(value="图像名称" ,required=false,hidden =true)
    private String labeleImgName;

    private static final long serialVersionUID = 1L;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getFloorCode() {
        return floorCode;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    public String getSpecialtyCode() {
        return specialtyCode;
    }

    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

  

    public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public String getPartPositionX() {
        return partPositionX;
    }

    public void setPartPositionX(String partPositionX) {
        this.partPositionX = partPositionX;
    }

    public String getPartPositionY() {
        return partPositionY;
    }

    public void setPartPositionY(String partPositionY) {
        this.partPositionY = partPositionY;
    }

    public String getPartPositionZ() {
        return partPositionZ;
    }

    public void setPartPositionZ(String partPositionZ) {
        this.partPositionZ = partPositionZ;
    }

    public String getLabelContent() {
        return labelContent;
    }

    public void setLabelContent(String labelContent) {
        this.labelContent = labelContent;
    }

    public String getLabelDate() {
        return labelDate;
    }

    public void setLabelDate(String labelDate) {
        this.labelDate = labelDate;
    }

    public String getLabelAudioPath() {
        return labelAudioPath;
    }

    public void setLabelAudioPath(String labelAudioPath) {
        this.labelAudioPath = labelAudioPath;
    }

    public String getLabelAudioName() {
        return labelAudioName;
    }

    public void setLabelAudioName(String labelAudioName) {
        this.labelAudioName = labelAudioName;
    }

    public String getLabeleImgPath() {
        return labeleImgPath;
    }

    public void setLabeleImgPath(String labeleImgPath) {
        this.labeleImgPath = labeleImgPath;
    }

    public String getLabeleImgName() {
        return labeleImgName;
    }

    public void setLabeleImgName(String labeleImgName) {
        this.labeleImgName = labeleImgName;
    }

  
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projectCode=").append(projectCode);
        sb.append(", buildingCode=").append(buildingCode);
        sb.append(", floorCode=").append(floorCode);
        sb.append(", specialtyCode=").append(specialtyCode);
        sb.append(", partCode=").append(partCode);
        sb.append(", partPositionX=").append(partPositionX);
        sb.append(", partPositionY=").append(partPositionY);
        sb.append(", partPositionZ=").append(partPositionZ);
        sb.append(", labelContent=").append(labelContent);
        sb.append(", labelDate=").append(labelDate);
        sb.append(", labelAudioPath=").append(labelAudioPath);
        sb.append(", labelAudioName=").append(labelAudioName);
        sb.append(", labeleImgPath=").append(labeleImgPath);
        sb.append(", labeleImgName=").append(labeleImgName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}