package com.mektec.vms.domain.api;

import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;


@XmlRootElement(name = "DefectRecord")
public class ApiDefectRecord {



    private String recordId;

    private String terminalCode;
    private String lotNum;
    private int count;
    private String defectId;

    private Timestamp recordTime;  //具体的时间
    private String goalTerminal;

    public int getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    private int peopleNumber;


    public String getGoalTerminal() {
        return goalTerminal;
    }

    public void setGoalTerminal(String goalTerminal) {
        this.goalTerminal = goalTerminal;
    }



    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getDefectId() {
        return defectId;
    }

    public void setDefectId(String defectId) {
        this.defectId = defectId;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }
}
