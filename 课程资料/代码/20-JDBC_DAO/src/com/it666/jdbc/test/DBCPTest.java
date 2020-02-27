package com.it666.jdbc.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

import com.it666.jdbc.util.JdbcUtil;

public class DBCPTest {
	public static void main(String[] args) throws Exception {

		String url = "jdbc:mysql://localhost:3306/jdbc_db?rewriteBatchedStatements=true";
		String user = "root";
		String pwd = "1234";
		String driverName = "com.mysql.jdbc.Driver";

		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverName);
		ds.setUsername(user);
		ds.setPassword(pwd);
		ds.setUrl(url);

		Connection conn = ds.getConnection();
		String sql = "select * from account";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			String name = rs.getString("name");
			double money = rs.getDouble("money");
			System.out.println(name+"===="+money);
		}
		JdbcUtil.close(conn, st, rs);
	}
}
