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
public class AppConfig {

	@Bean
	public PooledDataSource dataSource() {
        //PooledDataSource
        PooledDataSource dataSrc = new PooledDataSource();

		Properties properties = null;

		if(PropertiesManager.exists(PropertiesManager.DATA_SOURCE_FILE)) {
			//从外部加载
			properties = PropertiesManager.loadFromExternal(
					PropertiesManager.DATA_SOURCE_FILE);
		}

        //如果从外部加载失败，则从内部资源加载
		if(properties == null
                || properties.getProperty("jdbc.driverClassName") == null
                || properties.getProperty("jdbc.driverClassName").isEmpty()) {

			properties = PropertiesManager.loadFromResource(
					PropertiesManager.DATA_SOURCE_FILE);
		}


		dataSrc.setDriver(properties.getProperty("jdbc.driverClassName"));
		dataSrc.setUrl(properties.getProperty("jdbc.url"));
		dataSrc.setUsername(properties.getProperty("jdbc.username"));
		dataSrc.setPassword(properties.getProperty("jdbc.password"));
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

	@Bean
	public SqlScriptService sqlScriptService() {
		return new SqlScriptService();
	}

	@Bean
	public EmployeeService employeeService() {
		return new EmployeeServiceImpl();
	}

	@Bean
	public UserService userService() {return new UserServiceImpl();}

	@Bean
	public LocationService locationService(){return new LocationServiceImpl();}

	@Bean
	public SettingService settingService(){return new SettingServiceImpl();}


}
