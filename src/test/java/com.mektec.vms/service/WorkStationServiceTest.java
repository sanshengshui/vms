package com.mektec.vms.service;


import static org.junit.Assert.*;

import com.mektec.vms.domain.ProductLine;
import com.mektec.vms.domain.WorkStation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mektec.common.UID;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.mektec.vms.config.AppConfigForTest.class})
public class WorkStationServiceTest {

	private SqlScriptService sqlScriptService;
	private WorkStationService workStationService;

	@Before
	public void setUp() throws Exception {
		sqlScriptService.executeScript(SqlScriptService.DbType.H2DB, "create-tables.sql");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testWorkStation() {
        ProductLine productLine = new ProductLine();
        productLine.setLineId("L001");
		WorkStation workStation= new WorkStation();
		workStation.setStationId(UID.generate("C"));
		workStation.setStationName("CTEST");
		workStation.setCreateUser("ydd");
		workStation.setCreateTime(new Timestamp(new Date().getTime()));
		workStation.setDescription("站台");
		workStation.setSn(1);
        //workStation.setProductLine(productLine);

		workStationService.createWorkStation(workStation);
		WorkStation wk = workStationService.findWorkStationById(workStation.getStationId());
		assertNotNull(wk);

		workStationService.updateWorkStation(workStation);
		 wk = workStationService.findWorkStationById(workStation.getStationId());
		assertNotNull(wk);

		workStationService.deleteWorkStation(workStation);
		wk = workStationService.findWorkStationById(workStation.getStationId());
		assertTrue(wk.isDeleted());
	}

	@Autowired
	public void setWorkStationService(WorkStationService workStationService) {
		this.workStationService = workStationService;
	}

	@Autowired
	public void setSqlScriptService(SqlScriptService sqlScriptService) {
		this.sqlScriptService = sqlScriptService;
	}
}
