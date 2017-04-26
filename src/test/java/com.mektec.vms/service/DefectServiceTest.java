package com.mektec.vms.service;

import com.mektec.vms.domain.Defect;
import com.mektec.vms.domain.ProductLine;
import com.mektec.vms.domain.WorkStation;
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
public class DefectServiceTest {

    private DefectService defectService;
    private SqlScriptService sqlScriptService;

    @Before
    public void setUp() throws Exception {
        sqlScriptService.executeScript(SqlScriptService.DbType.H2DB, "create-tables.sql");
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testDefect(){
        Defect defect = new Defect();
        WorkStation workStation = new WorkStation();
        ProductLine line = new ProductLine();
        defect.setWorkStation(workStation);
        defect.setDefectId("12");
        defect.setDefectName("daa");
        defect.setShortcut("123");
        defect.setBaseId("31");


        defectService.createDefect(defect);
        Defect def = defectService.findDefectById(defect.getDefectId());
        assertNotNull(def);

        defectService.updateDefect(defect);
        def = defectService.findDefectById(defect.getDefectId());
        assertNotNull(def);

        defectService.findDefectListByLine(line.getLineId());
        def = defectService.findDefectById(defect.getDefectId());
        assertNotNull(def);

        defectService.deleteDefect(defect);
        def = defectService.findDefectById(defect.getDefectId());
        assertTrue(def.isDeleted());

    }

    @Autowired
    public void setDefectService(DefectService defectService) {
        this.defectService = defectService;
    }
    @Autowired
    public void setSqlScriptService(SqlScriptService sqlScriptService) {
        this.sqlScriptService = sqlScriptService;
    }
}
