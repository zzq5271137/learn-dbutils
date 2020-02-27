package com.it666.jdbc.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {

	public static void main(String[] args) throws Exception {

		// 1.加载驱动
		// 把com.mysql.jdbc.Driver这份字节码加载进JVM
		// 当一份字节码被加载到JVM时，就会执行该字节码中的静态代码块
		Class.forName("com.mysql.jdbc.Driver");

		// 2.获取连接对象
		// url 数据库地址
		String url = "jdbc:mysql://localhost:3306/jdbc_db";
		// 用户名
		String user = "root";
		// 密码
		String password = "1234";
		Connection conn = DriverManager.getConnection(url, user, password);
		
		//3.编写sql语句
		String sql = "create table stu(id int,name varchar(50),age int)";
		Statement st = conn.createStatement();		
		//4.执行sql
		int row =  st.executeUpdate(sql);
		//5.释放资源 
		st.close();
		conn.close();
	}

}
