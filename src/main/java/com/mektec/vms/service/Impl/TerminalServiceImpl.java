package com.mektec.vms.service.Impl;

import com.mektec.vms.domain.Terminal;
import com.mektec.vms.mapper.TerminalMapper;
import com.mektec.vms.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
@Service
@Transactional
public class TerminalServiceImpl implements TerminalService{

    private TerminalMapper terminalMapper;

    @Autowired
    public void setTerminalMapper(TerminalMapper terminalMapper) {
        this.terminalMapper = terminalMapper;
    }

    @Override
    public void createTerminal(Terminal terminal) {
        terminalMapper.createTerminal(terminal);
    }


    @Override
    public Terminal findTerminalByStation(String stationId){
        return  terminalMapper.findTerminalByStation(stationId);
    }

    @Override
    public Terminal findTerminalById(String terminalId) {

        return terminalMapper.findTerminalById(terminalId);
    }

    @Override
    public Terminal findTerminalByCode(String terminalCode) {

        return terminalMapper.findTerminalByCode(terminalCode);
    }

    @Override
    public List<Terminal> findTerminalListByStationId(String terminalCode){
        return terminalMapper.findTerminalListByStationId(terminalCode);
    };

    @Override
    public List<Terminal> findTerminalByStationId(String stationId) {
        return terminalMapper.findTerminalByStationId(stationId);
    }

    @Override
    public void updateTerminal(Terminal terminal) {
        terminalMapper.updateTerminal(terminal);
    }

    @Override
    public void deleteTerminal(Terminal terminal) {
    terminalMapper.deleteTerminal(terminal);
    }

    @Override
    public List<Terminal> findAllTerminalOfLine(String lineId) {
        return terminalMapper.findAllTerminalOfLine(lineId);
    }
}
