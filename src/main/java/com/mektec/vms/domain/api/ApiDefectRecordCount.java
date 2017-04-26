package com.mektec.vms.domain.api;

import com.mektec.vms.domain.DefectRecord;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mektec on 16-9-1.
 */
@XmlRootElement(name = "DefectRecord")
public class ApiDefectRecordCount {
    private String defectId;
    private int date;
    private int count;

    public static ApiDefectRecordCount fromDefectRecord(DefectRecord defectRecord)
    {
        ApiDefectRecordCount defectRecordCount=new ApiDefectRecordCount();
        defectRecordCount.setDefectId(defectRecordCount.getDefectId());
        defectRecordCount.setDate(defectRecordCount.getDate());
        defectRecordCount.setCount(defectRecordCount.getCount());
        return defectRecordCount;
    }

    public String getDefectId() {
        return defectId;
    }

    public void setDefectId(String defectId) {
        this.defectId = defectId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getResponser() {
        return responser;
    }

    public void setResponser(String responser) {
        this.responser = responser;
    }

    private String responser;
}
