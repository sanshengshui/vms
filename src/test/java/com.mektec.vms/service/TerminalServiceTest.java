package com.mektec.vms.service;

import com.mektec.common.UID;
import com.mektec.vms.domain.Terminal;
import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.mapper.TerminalMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;
import static org.junit.Assert.*;
/**
 * Created by mektec on 16-4-8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.mektec.vms.config.AppConfigForTest.class})
public class TerminalServiceTest {

    private SqlScriptService sqlScriptService;
    private TerminalService terminalService;

    @Before
    public void setUp() throws Exception {
        sqlScriptService.executeScript(SqlScriptService.DbType.H2DB, "create-tables.sql");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testTerminal(){
        WorkStation workStation = new WorkStation();
        Terminal terminal = new Terminal();
        terminal.setTerminalId(UID.generate("T1"));
        terminal.setTerminalName("终端T");
        terminal.setCreateUser("ydd");
        terminal.setTerminalType("接收端");
        terminal.setCreateTime(new Timestamp(new Date().getTime()));
        terminal.setOperator("小凡");
        terminal.setUpdateUser("小凡");
        terminal.setTerminalCode("T20160408");
        terminal.setUpdateTime(new Timestamp(new Date().getTime()));
        terminal.setWorkStation(workStation);

        terminalService.createTerminal(terminal);
        Terminal tm = terminalService.findTerminalById(terminal.getTerminalId());
        assertNotNull(tm);

        terminalService.updateTerminal(terminal);
        tm = terminalService.findTerminalById(terminal.getTerminalId());
        assertNotNull(tm);

        terminalService.deleteTerminal(terminal);
        tm = terminalService.findTerminalById(terminal.getTerminalId());
        assertTrue(tm.isDeleted());
    }

    @Autowired
    public void setSqlScriptService(SqlScriptService sqlScriptService) {
        this.sqlScriptService = sqlScriptService;
    }
    @Autowired
    public void setTerminalService(TerminalService terminalService) {
        this.terminalService = terminalService;
    }
}
