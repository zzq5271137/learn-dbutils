package com.it666.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.it666.jdbc.util.JdbcUtil;

public class TransactionTest {

	public static void main(String[] args) throws Exception {
		/**
		1.检查zs账户余额
		2.减少zs账户1000
		3.增加ls账户1000
		 */
		Connection conn = JdbcUtil.getConn();
		String sql = "select * from account where name=? and money>?";
		// 创建语句
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "zs");
		ps.setInt(2, 1000);
		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			throw new RuntimeException("没钱了");
		}
		try {
			//开启事务
			conn.setAutoCommit(false);
			// 2.减少zs账户1000
			sql = "update account set money = money - ? where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, 1000);
			ps.setString(2, "zs");
			ps.executeUpdate();
			int a = 1 / 0;
			// 3.增加ls账户1000
			sql = "update account set money = money + ? where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, 1000);
			ps.setString(2, "ls");
			ps.executeUpdate();
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
		
		
	}

}
