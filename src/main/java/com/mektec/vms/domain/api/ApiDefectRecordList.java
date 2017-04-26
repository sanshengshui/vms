package com.mektec.vms.domain.api;

import com.mektec.vms.domain.DefectRecord;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mektec on 16-10-28.
 */

@XmlRootElement(name = "DefectRecord")
public class ApiDefectRecordList {
    private String defectId;
    private int date;
    private int count;
    private String responser;
    private String recordTime;

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    private int percent;

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public static  ApiDefectRecordList formDefectRecordList(DefectRecord defectRecord){
        ApiDefectRecordList defectRecordCount=new ApiDefectRecordList();
        defectRecordCount.setDefectId(defectRecord.getDefectId());
        defectRecordCount.setDate(defectRecord.getDate());
        defectRecordCount.setResponser(defectRecord.getResponser());
        defectRecordCount.setCount(defectRecord.getCount());
        defectRecordCount.setRecordTime(defectRecord.getRecordTime());
        defectRecordCount.setPercent(defectRecord.getPercent());

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
}
