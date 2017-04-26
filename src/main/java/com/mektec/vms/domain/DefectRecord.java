package com.mektec.vms.domain;


import java.sql.Timestamp;

/**
 * 缺陷记录
 * Created by mektec on 16-3-30.
 */

public class DefectRecord {
    private String DefectId;
    private String goalTerminal;
    private int peopleNumber;

    public String getGoalTerminal() {
        return goalTerminal;
    }

    public void setGoalTerminal(String goalTerminal) {
        this.goalTerminal = goalTerminal;
    }

    public int getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    private String recordId;
    private Defect defect;
    private String terminalId;
    private String stationId;
    private String lotNum;
    private int count;
    private int date;
    private int time;  //只记录到分
    private String recorder;
    private String responser;
    private String recordTime;  //具体的时间
    private Boolean deleted;

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

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Defect getDefect() {
        return defect;
    }

    public void setDefect(Defect defect) {
        this.defect = defect;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public int getCount() {
        return count;
    }

    public String getstationId() {
        return stationId;
    }

    public void setstationId(String workStationId) {
        this.stationId = workStationId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getResponser() {
        return responser;
    }

    public void setResponser(String responser) {
        this.responser = responser;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }



    public String getDefectId() {
        return DefectId;
    }

    public void setDefectId(String defectId) {
        DefectId = defectId;
    }
}
