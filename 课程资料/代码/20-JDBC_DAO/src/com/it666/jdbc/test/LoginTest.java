package com.it666.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.it666.jdbc.util.JdbcUtil;
import com.mysql.jdbc.JDBC4PreparedStatement;

public class LoginTest {
	
	@Test
	public void test() throws Exception {
		System.out.println(this.login("' OR 1=1 OR'", "123"));
		//select * from user where name = '' OR 1=1 OR'' and pwd = '123'
	}

	
	String login(String userName,String pwd) throws Exception {
		//1.连接数据库
		Connection conn = JdbcUtil.getConn();
		//2.查看有没有用户名为传入的，密码同时正确
		String sql = "select * from user where name =? and pwd = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userName);
		ps.setString(2, pwd);
		System.out.println(((JDBC4PreparedStatement)ps).asSql());
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			JdbcUtil.close(conn, ps, rs);
			return "登录成功";
		}else {
			JdbcUtil.close(conn, ps, rs);
			return "登录失败";
		}
	}
}
