package com.it666.jdbc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.it666.jdbc.domain.Student;
import com.it666.jdbc.handler.IResultSetHandler;

public class CRUDTemplate {
	/**
	 * 
	1.设计一个方法
	2.要求传入两个参数
	一个sql语句
	一个参数
		第一个参数sql语句模板
		第二个参数为可变参数，设置语句参数值
	3.返回值
	返回值为int，受影响的行数。
	可变参数的本质是一个数组
	 */
	public static int executeUpdate(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1.加载驱动
			// 2.连接数据库
			conn = JdbcUtil.getConn();
			// 3.创建语句
			ps = conn.prepareStatement(sql);
			// 遍历参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// 4.执行语句
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.释放资源
			JdbcUtil.close(conn, ps, null);
		}
		return 0;
	}

	public static <T>T executeQuery(String sql, IResultSetHandler<T> rh, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// 1.加载驱动
			// 2.连接数据库
			conn = JdbcUtil.getConn();
			// 3.创建语句
			ps = conn.prepareStatement(sql);
			// 遍历参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// 4.执行语句
			rs = ps.executeQuery();
			return rh.handle(rs);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.释放资源
			JdbcUtil.close(conn, ps, rs);
		}
		return null;
	}

}
