package com.mektec.vms.domain.api;

import com.mektec.vms.domain.Terminal;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mektec on 16-10-13.
 */
@XmlRootElement(name = "TerminalList")
public class ApiTerminalList {
    private String terminalCode;
    private String terminalType;

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public static ApiTerminalList fromApiTerminalList (Terminal terminal){
        ApiTerminalList apiTerminalList=new ApiTerminalList();
        apiTerminalList.setTerminalType(terminal.getTerminalType());

        apiTerminalList.setTerminalCode(terminal.getTerminalCode());

        return  apiTerminalList;

    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }
}
