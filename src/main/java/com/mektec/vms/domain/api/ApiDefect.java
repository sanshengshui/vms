package com.mektec.vms.domain.api;

import com.mektec.vms.domain.Defect;


import com.mektec.vms.domain.WorkStation;


import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

/**
 * 缺陷
 * Created by mektec on 16-3-30.
 */
@XmlRootElement(name = "Defect")
public class ApiDefect {

    private String defectId;
    private String defectName;
    private String shortcut;
    private String stationId;
    private String stationName;
    public String getStationId() {
        return stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }





    public static ApiDefect fromDefect(Defect defect) {
        ApiDefect apiDefect = new ApiDefect();
        apiDefect.setDefectId(defect.getDefectId());
        apiDefect.setDefectName(defect.getDefectName());
        apiDefect.setShortcut(defect.getShortcut());
        apiDefect.setStationId(defect.getStationId());
        apiDefect.setStationName(defect.getStationName());







        return  apiDefect;
    }

    public String getDefectName() {
        return defectName;
    }

    public void setDefectName(String defectName) {
        this.defectName = defectName;
    }

    public String getDefectId() {
        return defectId;
    }

    public void setDefectId(String defectId) {
        this.defectId = defectId;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }


}
