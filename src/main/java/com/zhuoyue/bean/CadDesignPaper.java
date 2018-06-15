package com.zhuoyue.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 
 */
public class CadDesignPaper implements Serializable {
    private String id;

    private String cadFileName;

    private String projectId;

    private String buildingId;

    private String floorId;

    private String specialtyId;

    private String cadFileSize;

    private String cadFileType;

    private Date createTime;

    private byte[] cadFile;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCadFileName() {
        return cadFileName;
    }

    public void setCadFileName(String cadFileName) {
        this.cadFileName = cadFileName;
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

	public String getCadFileSize() {
        return cadFileSize;
    }

    public void setCadFileSize(String cadFileSize) {
        this.cadFileSize = cadFileSize;
    }

    public String getCadFileType() {
        return cadFileType;
    }

    public void setCadFileType(String cadFileType) {
        this.cadFileType = cadFileType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public byte[] getCadFile() {
        return cadFile;
    }

    public void setCadFile(byte[] cadFile) {
        this.cadFile = cadFile;
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
        CadDesignPaper other = (CadDesignPaper) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCadFileName() == null ? other.getCadFileName() == null : this.getCadFileName().equals(other.getCadFileName()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getBuildingId() == null ? other.getBuildingId() == null : this.getBuildingId().equals(other.getBuildingId()))
            && (this.getFloorId() == null ? other.getFloorId() == null : this.getFloorId().equals(other.getFloorId()))
            && (this.getSpecialtyId() == null ? other.getSpecialtyId() == null : this.getSpecialtyId().equals(other.getSpecialtyId()))
            && (this.getCadFileSize() == null ? other.getCadFileSize() == null : this.getCadFileSize().equals(other.getCadFileSize()))
            && (this.getCadFileType() == null ? other.getCadFileType() == null : this.getCadFileType().equals(other.getCadFileType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (Arrays.equals(this.getCadFile(), other.getCadFile()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCadFileName() == null) ? 0 : getCadFileName().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getBuildingId() == null) ? 0 : getBuildingId().hashCode());
        result = prime * result + ((getFloorId() == null) ? 0 : getFloorId().hashCode());
        result = prime * result + ((getSpecialtyId() == null) ? 0 : getSpecialtyId().hashCode());
        result = prime * result + ((getCadFileSize() == null) ? 0 : getCadFileSize().hashCode());
        result = prime * result + ((getCadFileType() == null) ? 0 : getCadFileType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + (Arrays.hashCode(getCadFile()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cadFileName=").append(cadFileName);
        sb.append(", projectId=").append(projectId);
        sb.append(", buildingId=").append(buildingId);
        sb.append(", floorId=").append(floorId);
        sb.append(", specialtyId=").append(specialtyId);
        sb.append(", cadFileSize=").append(cadFileSize);
        sb.append(", cadFileType=").append(cadFileType);
        sb.append(", createTime=").append(createTime);
        sb.append(", cadFile=").append(cadFile);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}