package com.gome.domain;

import java.util.Date;

public class OrgInfo {
    private String orgNo;

    private String branchNo;

    private String superNo;

    private String branchClass;

    private String branchPhone;

    private String branchCity;

    private String branchAddr;

    private String branchDisc;

    private Long treeId;

    private Long treePid;

    private Date createTime;

    private Date updateTime;

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo == null ? null : branchNo.trim();
    }

    public String getSuperNo() {
        return superNo;
    }

    public void setSuperNo(String superNo) {
        this.superNo = superNo == null ? null : superNo.trim();
    }

    public String getBranchClass() {
        return branchClass;
    }

    public void setBranchClass(String branchClass) {
        this.branchClass = branchClass == null ? null : branchClass.trim();
    }

    public String getBranchPhone() {
        return branchPhone;
    }

    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone == null ? null : branchPhone.trim();
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity == null ? null : branchCity.trim();
    }

    public String getBranchAddr() {
        return branchAddr;
    }

    public void setBranchAddr(String branchAddr) {
        this.branchAddr = branchAddr == null ? null : branchAddr.trim();
    }

    public String getBranchDisc() {
        return branchDisc;
    }

    public void setBranchDisc(String branchDisc) {
        this.branchDisc = branchDisc == null ? null : branchDisc.trim();
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreePid() {
        return treePid;
    }

    public void setTreePid(Long treePid) {
        this.treePid = treePid;
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