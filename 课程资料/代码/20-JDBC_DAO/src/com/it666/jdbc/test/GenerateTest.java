package com.it666.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.it666.jdbc.util.JdbcUtil;

public class GenerateTest {
	public static void main(String[] args) throws Exception {
		
		 Connection conn = JdbcUtil.getConn();
		 String sql = "insert into student (name,age) values (?,?)";
		//设置可以获取主键
		 PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		 ps.setString(1, "李四");
		 ps.setInt(2, 20);
		 ps.executeUpdate();
		 
		 ResultSet rs = ps.getGeneratedKeys();
		 if(rs.next()) {
			  int id = rs.getInt(1);
			  System.out.println(id);
		  }
		 JdbcUtil.close(conn, ps, null);
	
	}
	
	void test() throws Exception {
		 Connection conn = JdbcUtil.getConn();
		 String sql = "insert into student (name,age) values ('张三',30)";
		 
		 Statement st = conn.createStatement();
		 //设置可以获取主键
		 st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		 //获取自动生成的id
		  ResultSet rs = st.getGeneratedKeys();
		  if(rs.next()) {
			  int id = rs.getInt(1);
			  System.out.println(id);
		  }
		 JdbcUtil.close(conn, st, null);
	}
	
}
