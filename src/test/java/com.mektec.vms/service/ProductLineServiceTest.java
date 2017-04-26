package com.mektec.vms.service;

import com.mektec.vms.domain.Location;
import com.mektec.vms.domain.ProductLine;
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
public class ProductLineServiceTest {

    private SqlScriptService sqlScriptService;
    private ProductLineService productLineService;

    @Before
    public void setUp() throws Exception {
        sqlScriptService.executeScript(SqlScriptService.DbType.H2DB, "create-tables.sql");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testProductLine(){
        ProductLine productLine =new ProductLine();
        Location location = new Location();
        productLine.setLineId("1134");
        productLine.setLineCode("1134");
        productLine.setCreateUser("ydd");
        productLine.setUpdateUser("yxf");
        productLine.setLocation(location);

        productLineService.createLine(productLine);
        ProductLine pl = productLineService.findLineById(productLine.getLineId());
        assertNotNull(pl);

        productLineService.updateLine(productLine);
        pl = productLineService.findLineById(productLine.getLineId());
        assertNotNull(pl);


        productLineService.deleteLine(productLine);
		pl = productLineService.findLineById(productLine.getLineId());
		assertTrue(pl.isDeleted());
    }

    @Autowired
    public void setProductLineService(ProductLineService productLineService) {
        this.productLineService = productLineService;
    }

    @Autowired
    public void setSqlScriptServiceTest(SqlScriptService sqlScriptService) {
        this.sqlScriptService = sqlScriptService;
    }
}
