package com.mektec.vms.service;

import com.mektec.vms.domain.Terminal;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
public interface TerminalService {

    void createTerminal(Terminal terminal);
    Terminal findTerminalById(String terminalId);
    Terminal findTerminalByCode(String terminalCode);
    List<Terminal> findTerminalListByStationId(String terminalCode);

    List<Terminal> findTerminalByStationId(String stationId);
    Terminal findTerminalByStation(String stationId);
    void updateTerminal(Terminal terminal);
    void deleteTerminal(Terminal terminal);

    List<Terminal> findAllTerminalOfLine(String lineId);
}
