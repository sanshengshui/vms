package com.mektec.vms.domain.api;


import com.mektec.vms.domain.Terminal;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;



@XmlRootElement(name = "Terminal")
public class ApiTerminal {
    private String terminalCode;
    private String terminalType;
    private Timestamp updateTime;
    private String stationId;
    private String operator;

    public ApiTerminal(Terminal terminal) {
        terminalCode = terminal.getTerminalCode();
        terminalType = terminal.getTerminalType();
        updateTime = terminal.getUpdateTime();
        stationId = terminal.getWorkStation().getStationId();
        operator = terminal.getOperator();
    }
    public ApiTerminal(){


    }




    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }
}
