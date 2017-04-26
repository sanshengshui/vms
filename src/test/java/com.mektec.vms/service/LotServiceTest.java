package com.mektec.vms.service;

import com.mektec.vms.domain.Lot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by mektec on 16-4-8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.mektec.vms.config.AppConfigForTest.class})
public class LotServiceTest {

    private LotService lotService;
    private SqlScriptService sqlScriptService;

    @Before
    public void setUp() throws Exception {
        sqlScriptService.executeScript(SqlScriptService.DbType.H2DB, "create-tables.sql");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testLot(){
        Lot lot =new Lot();
        lot.setLotNum("123123");
        lot.setCount(21);
        lot.setModelNum("123123");

        lotService.createLot(lot);
        Lot lot1 = lotService.findLotByLotNum(lot.getLotNum());
        assertNotNull(lot1);

        lotService.updateLot(lot);
        lot1 = lotService.findLotByLotNum(lot.getLotNum());
        assertNotNull(lot1);

        lotService.deleteLot(lot);
        lot1 = lotService.findLotByLotNum(lot.getLotNum());
        assertTrue(lot1.isDeleted());
    }

    @Autowired
    public void setLotService(LotService lotService) {
        this.lotService = lotService;
    }
    @Autowired
    public void setSqlScriptService(SqlScriptService sqlScriptService) {
        this.sqlScriptService = sqlScriptService;
    }
}
