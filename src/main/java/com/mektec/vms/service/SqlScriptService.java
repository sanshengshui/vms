package com.mektec.vms.service;

import com.mektec.vms.config.PropertiesManager;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

@Service
public class SqlScriptService {
    public static enum DbType {
        MYSQL,
        MSSQL,
        PGSQL,
        H2DB
    }

    private PooledDataSource dataSource;

    public void execute(DbType dbType, String sql) {
        Connection con;
        try {
            sql = convertScript(dbType,sql);

            if ( sql.isEmpty()) {
                return;
            }
            con = dataSource.getConnection();
            Statement stmt = con.createStatement() ;
            stmt.execute(sql);
            con.commit();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

	public void executeScript(DbType dbType, String script) {
		String sql = readScript(script);
        execute(dbType, sql);
	}

    private String convertScript(DbType dbType, String script) {
        return script;
    }

	private String readScript(String script) {
		InputStream in = SqlScriptService.class.getClassLoader().getResourceAsStream(script);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder strBuilder = new StringBuilder();
        String line = null;
        try {
        	while ((line = reader.readLine()) != null) {
        		strBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strBuilder.toString();
	}

	@Autowired
	public void setDataSource(PooledDataSource dataSource) {
		this.dataSource = dataSource;
	}

}
