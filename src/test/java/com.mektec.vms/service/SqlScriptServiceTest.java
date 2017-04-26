package com.mektec.vms.service;


import com.mektec.common.UID;
import com.mektec.vms.domain.WorkStation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.mektec.vms.config.AppConfigForTest.class})
public class SqlScriptServiceTest {

	@Autowired 
	private SqlScriptService sqlScriptService;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testRunScript() {
		sqlScriptService.executeScript(SqlScriptService.DbType.H2DB, "create-tables.sql");
	}
	
}
