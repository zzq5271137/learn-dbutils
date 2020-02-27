package com.it666.jdbc.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.it666.jdbc.dao.IStudentDao;
import com.it666.jdbc.domain.Student;
import com.it666.jdbc.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {
	
	@Override
	public void save(Student stu) {
		String sql = "insert into student(name,age) values(?,?)";
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		try {
			qr.update(sql,stu.getName(),stu.getAge());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(int id) {
		// 3.´´½¨Óï¾ä
		String sql = "delete from student where id = ?";
	}
	@Override
	public void update(int id, Student stu) {
		String sql = "update student set name=?, age=? where id =? ";
	}
	@Override
	public Student get(int id) {
		
		String sql = "select * from student where id = ?";
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		try {
			return qr.query(sql, new BeanHandler<Student>(Student.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Student> getAll() {
		String sql = "select * from student ";
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		try {
			return qr.query(sql, new BeanListHandler<Student>(Student.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void test() {
		String sql = "select * from student ";
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		try {
			 List<Map<String, Object>> res = qr.query(sql, new MapListHandler());
			 System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Integer getCount() {
		String sql = "select count(*) as count from student";
		return null;
	}
}










