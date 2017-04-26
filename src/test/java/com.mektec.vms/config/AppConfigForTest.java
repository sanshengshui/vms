package com.mektec.vms.config;

import com.mektec.vms.service.*;
import com.mektec.vms.service.Impl.*;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.mektec.vms.service",
        "com.mektec.vms.config"})
@MapperScan(basePackages = {"com.mektec.vms.mapper"})
public class AppConfigForTest {
		
	@Bean
	public PooledDataSource dataSource() {
        //PooledDataSource
        PooledDataSource dataSrc = new PooledDataSource();

		dataSrc.setDriver("org.h2.Driver");
		dataSrc.setUrl("jdbc:h2:mem:test");
		dataSrc.setUsername("sa");
		dataSrc.setPassword("");
        dataSrc.setPoolMaximumActiveConnections(100);
        dataSrc.setPoolMaximumCheckoutTime(20000);
        dataSrc.setPoolMaximumIdleConnections(10);
        dataSrc.setPoolTimeToWait(20000);
		return dataSrc;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("com.mektec.vms.domain");
		return sessionFactory.getObject();
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		SqlSessionTemplate session = new SqlSessionTemplate(sqlSessionFactory());
		return session;
	}

	@Bean
	public DefectRecordService defectRecordService(){return new DefectRecordServiceImpl();}

	@Bean
	public DefectService defectService(){return new DefectServiceImpl();}

	@Bean
	public ProductLineService productLineService(){return new ProductLineServiceImpl();}

	@Bean
	public LotService lotService(){return new LotServiceImpl();}

	@Bean
	public TerminalService terminalService(){return new TerminalServiceImpl();}

	@Bean
	public WorkStationService workStationService(){return new WorkStationServiceImpl();}

	@Bean SqlScriptService sqlScriptService() {
		return new SqlScriptService();
	}
}
