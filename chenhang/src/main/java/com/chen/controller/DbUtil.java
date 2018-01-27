package com.chen.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.ibatis.jdbc.ScriptRunner;

public class DbUtil {

	public static void executeSqlFileByMysql(String sqlFilePath) throws Exception {
		Properties prop = new Properties();
		InputStream ins = DbUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(ins);
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		ins.close();

		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			ScriptRunner runner = new ScriptRunner(conn);
			runner.setAutoCommit(true);// 自动提交
			runner.setFullLineDelimiter(false);
			runner.setDelimiter(";");//// 每条命令间的分隔符
			runner.setSendFullScript(false);
			runner.setStopOnError(false);
			// 如果有多个sql文件，可以写多个runner.runScript(xxx),
			runner.runScript(new InputStreamReader(new FileInputStream(sqlFilePath), "utf-8"));
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}