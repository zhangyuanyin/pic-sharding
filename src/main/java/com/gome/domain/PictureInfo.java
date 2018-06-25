package com.gome.domain;

import java.util.Date;

public class PictureInfo {
    private String picId;

    private String appNo;

    private String imgName;

    private String saveName;

    private String paperType;

    private String sysNo;

    private String sysName;

    private String idNo;

    private String idTpye;

    private String kId;

    private String picFlag;

    private String orgNo;

    private String branchId;

    private String isPatchBolt;

    private String isWaste;

    private String isSmall;

    private Long sortNo;

    private String remark;

    private Date createTime;

    private Date updateTime;

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId == null ? null : picId.trim();
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo == null ? null : appNo.trim();
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName == null ? null : saveName.trim();
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType == null ? null : paperType.trim();
    }

    public String getSysNo() {
        return sysNo;
    }

    public void setSysNo(String sysNo) {
        this.sysNo = sysNo == null ? null : sysNo.trim();
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getIdTpye() {
        return idTpye;
    }

    public void setIdTpye(String idTpye) {
        this.idTpye = idTpye == null ? null : idTpye.trim();
    }

    public String getkId() {
        return kId;
    }

    public void setkId(String kId) {
        this.kId = kId == null ? null : kId.trim();
    }

    public String getPicFlag() {
        return picFlag;
    }

    public void setPicFlag(String picFlag) {
        this.picFlag = picFlag == null ? null : picFlag.trim();
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    public String getIsPatchBolt() {
        return isPatchBolt;
    }

    public void setIsPatchBolt(String isPatchBolt) {
        this.isPatchBolt = isPatchBolt == null ? null : isPatchBolt.trim();
    }

    public String getIsWaste() {
        return isWaste;
    }

    public void setIsWaste(String isWaste) {
        this.isWaste = isWaste == null ? null : isWaste.trim();
    }

    public String getIsSmall() {
        return isSmall;
    }

    public void setIsSmall(String isSmall) {
        this.isSmall = isSmall == null ? null : isSmall.trim();
    }

    public Long getSortNo() {
        return sortNo;
    }

    public void setSortNo(Long sortNo) {
        this.sortNo = sortNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}