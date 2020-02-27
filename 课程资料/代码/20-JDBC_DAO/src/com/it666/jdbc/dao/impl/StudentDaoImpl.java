package com.it666.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.it666.jdbc.dao.IStudentDao;
import com.it666.jdbc.domain.Student;
import com.it666.jdbc.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {


	@Override
	public void save(Student stu) {

		Connection conn = null;
		PreparedStatement ps  = null;
		try {
			// 1.加载驱动
			// 2.连接数据库
			conn = JdbcUtil.getConn();
			// 3.创建语句
			
			String sql = "insert into student(name,age) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, stu.getName());
			ps.setInt(2, stu.getAge());
			
			// 4.执行语句
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.释放资源
			JdbcUtil.close(conn, ps, null);
		}
	}

	@Override
	public void delete(int id) {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1.加载驱动
			// 2.连接数据库
			conn = JdbcUtil.getConn();
			// 3.创建语句
			String sql = "delete from student where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			// 4.执行语句
			ps.executeUpdate();
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.释放资源
			JdbcUtil.close(conn, ps, null);
		}

	}

	@Override
	public void update(int id, Student stu) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1.加载驱动
			// 2.连接数据库
			conn = JdbcUtil.getConn();
			// 3.创建语句
			String sql = "update student set name=?, age=? where id =? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, stu.getName());
			ps.setInt(2, stu.getAge());
			ps.setInt(3, id);
			// 4.执行语句
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
	}

	@Override
	public Student get(int id) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 1.加载驱动
			// 2.连接数据库
			conn = JdbcUtil.getConn();
			// 3.创建语句
			String sql = "select * from student where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			// 4.执行语句
			rs = ps.executeQuery();
			if (rs.next()) {
				Student stu = new Student();
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
				stu.setId(rs.getInt("id"));
				return stu;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.释放资源
			JdbcUtil.close(conn, ps, rs);

		}
		return null;
	}

	@Override
	public List<Student> getAll() {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 1.加载驱动
			// 2.连接数据库
			conn = JdbcUtil.getConn();
			// 3.创建语句
			st = conn.createStatement();
			String sql = "select * from student ";
			System.out.println(sql);
			// 4.执行语句
			rs = st.executeQuery(sql);
			// 创建一个集合
			List<Student> list = new ArrayList<Student>();
			while (rs.next()) {
				Student stu = new Student();
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
				stu.setId(rs.getInt("id"));
				list.add(stu);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, st, rs);
		}
		return null;
	}
	
}
