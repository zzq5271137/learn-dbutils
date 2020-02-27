package com.it666.jdbc.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.it666.jdbc.dao.IStudentDao;
import com.it666.jdbc.domain.Student;
import com.it666.jdbc.handler.BeanHandler;
import com.it666.jdbc.handler.BeanListHandler;
import com.it666.jdbc.handler.IResultSetHandler;
import com.it666.jdbc.util.CRUDTemplate;

public class StudentDaoImpl implements IStudentDao {
	@Override
	public void save(Student stu) {
		String sql = "insert into student(name,age) values(?,?)";
		CRUDTemplate.executeUpdate(sql, stu.getName(), stu.getAge());
	}
	@Override
	public void delete(int id) {
		// 3.´´½¨Óï¾ä
		String sql = "delete from student where id = ?";
		CRUDTemplate.executeUpdate(sql, id);
	}
	@Override
	public void update(int id, Student stu) {
		String sql = "update student set name=?, age=? where id =? ";
		CRUDTemplate.executeUpdate(sql, stu.getName(), stu.getAge(), id);
	}
	@Override
	public Student get(int id) {
		String sql = "select * from student where id = ?";
	    return	CRUDTemplate.executeQuery(sql, new BeanHandler<Student>(Student.class), id);
	}
	@Override
	public List<Student> getAll() {
		String sql = "select * from student ";
		return CRUDTemplate.executeQuery(sql,new BeanListHandler<Student>(Student.class));		
	}
	@Override
	public Integer getCount() {
		String sql = "select count(*) as count from student";
		return CRUDTemplate.executeQuery(sql,new StuCountResultSetIml());	
	}
}


class StuCountResultSetIml implements IResultSetHandler<Integer>{
	@Override
	public Integer handle(ResultSet rs) throws Exception {
		if(rs.next()) {
		  return rs.getInt("count");
		}
		return 0;
	}
}









