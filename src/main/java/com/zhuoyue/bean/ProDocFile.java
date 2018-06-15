package com.zhuoyue.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 
 */
public class ProDocFile implements Serializable {
    private String id;

    private String projectId;

    private String buildingId;

    private String floorId;

    private String specialtyId;

    private String docFileSize;

    private String docFileType;

    private Date createTime;

    private String docFileName;

    private String docFileExte;

    private byte[] docFile;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(String specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getDocFileSize() {
        return docFileSize;
    }

    public void setDocFileSize(String docFileSize) {
        this.docFileSize = docFileSize;
    }

    public String getDocFileType() {
        return docFileType;
    }

    public void setDocFileType(String docFileType) {
        this.docFileType = docFileType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDocFileName() {
        return docFileName;
    }

    public void setDocFileName(String docFileName) {
        this.docFileName = docFileName;
    }

    public String getDocFileExte() {
        return docFileExte;
    }

    public void setDocFileExte(String docFileExte) {
        this.docFileExte = docFileExte;
    }

    public byte[] getDocFile() {
        return docFile;
    }

    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProDocFile other = (ProDocFile) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getBuildingId() == null ? other.getBuildingId() == null : this.getBuildingId().equals(other.getBuildingId()))
            && (this.getFloorId() == null ? other.getFloorId() == null : this.getFloorId().equals(other.getFloorId()))
            && (this.getSpecialtyId() == null ? other.getSpecialtyId() == null : this.getSpecialtyId().equals(other.getSpecialtyId()))
            && (this.getDocFileSize() == null ? other.getDocFileSize() == null : this.getDocFileSize().equals(other.getDocFileSize()))
            && (this.getDocFileType() == null ? other.getDocFileType() == null : this.getDocFileType().equals(other.getDocFileType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getDocFileName() == null ? other.getDocFileName() == null : this.getDocFileName().equals(other.getDocFileName()))
            && (this.getDocFileExte() == null ? other.getDocFileExte() == null : this.getDocFileExte().equals(other.getDocFileExte()))
            && (Arrays.equals(this.getDocFile(), other.getDocFile()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getBuildingId() == null) ? 0 : getBuildingId().hashCode());
        result = prime * result + ((getFloorId() == null) ? 0 : getFloorId().hashCode());
        result = prime * result + ((getSpecialtyId() == null) ? 0 : getSpecialtyId().hashCode());
        result = prime * result + ((getDocFileSize() == null) ? 0 : getDocFileSize().hashCode());
        result = prime * result + ((getDocFileType() == null) ? 0 : getDocFileType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getDocFileName() == null) ? 0 : getDocFileName().hashCode());
        result = prime * result + ((getDocFileExte() == null) ? 0 : getDocFileExte().hashCode());
        result = prime * result + (Arrays.hashCode(getDocFile()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectId=").append(projectId);
        sb.append(", buildingId=").append(buildingId);
        sb.append(", floorId=").append(floorId);
        sb.append(", specialtyId=").append(specialtyId);
        sb.append(", docFileSize=").append(docFileSize);
        sb.append(", docFileType=").append(docFileType);
        sb.append(", createTime=").append(createTime);
        sb.append(", docFileName=").append(docFileName);
        sb.append(", docFileExte=").append(docFileExte);
        sb.append(", docFile=").append(docFile);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}