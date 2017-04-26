package com.mektec.vms.domain;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 生产线
 * Created by mektec on 16-3-30.
 */

public class ProductLine {

    private String lineId;
    private String lineCode;
    private Location location;
    private String defectVersion;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String createUser;
    private String updateUser;
    private boolean deleted;
    private String failcount;
    private String passcount;
    private String sheetcount;


    public String getFailcount() {
        return failcount;
    }

    public void setFailcount(String failcount) {
        this.failcount = failcount;
    }

    public String getPasscount() {
        return passcount;
    }

    public void setPasscount(String passcount) {
        this.passcount = passcount;
    }

    public String getSheetcount() {
        return sheetcount;
    }

    public void setSheetcount(String sheetcount) {
        this.sheetcount = sheetcount;
    }

    public String toString() {
        return lineCode;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return (Timestamp) updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getDefectVersion() {
        return defectVersion;
    }

    public void setDefectVersion(String defectVersion) {
        this.defectVersion = defectVersion;
    }
}
