package com.zhuoyue.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel( value="labelVoForm" ,description="labelVoForm" )
public class LabelVo {
	
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

	@ApiModelProperty(value="时间")
    private String labelDate;
	 
	@ApiModelProperty(value="内容")
    private String labelContent;
	
	@ApiModelProperty(value="音频名称" , hidden = true)
	private String labelAudioName;
	
	@ApiModelProperty(value="图像名称" ,hidden = true )
	private String labeleImgName;
	
	
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

	public String getLabelDate() {
		return labelDate;
	}

	public void setLabelDate(String labelDate) {
		this.labelDate = labelDate;
	}

	
	
	public String getLabelContent() {
		return labelContent;
	}

	public void setLabelContent(String labelContent) {
		this.labelContent = labelContent;
	}

	public String getLabelAudioName() {
		return labelAudioName;
	}

	public void setLabelAudioName(String labelAudioName) {
		this.labelAudioName = labelAudioName;
	}

	public String getLabeleImgName() {
		return labeleImgName;
	}

	public void setLabeleImgName(String labeleImgName) {
		this.labeleImgName = labeleImgName;
	}

    
    
}
