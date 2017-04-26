package com.mektec.vms.domain.api;

import com.mektec.vms.domain.Terminal;

/**
 * Created by jamesmsw on 17-2-14.
 */
public class ApiAllTerminal {
    private String terminalCode;
    private String terminalName;

    public static ApiAllTerminal fromApiAllTerminalList (Terminal terminal){
        ApiAllTerminal apiAllTerminal=new ApiAllTerminal();
        apiAllTerminal.setTerminalCode(terminal.getTerminalCode());
        apiAllTerminal.setTerminalName(terminal.getTerminalName());


        return  apiAllTerminal;

    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }
}