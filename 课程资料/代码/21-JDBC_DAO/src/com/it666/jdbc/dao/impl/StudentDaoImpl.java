package com.it666.jdbc.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.it666.jdbc.dao.IStudentDao;
import com.it666.jdbc.domain.Student;
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
		IResultSetHandler<List<Student>> rh = new StuResultSetHandImp();
		List<Student> list = CRUDTemplate.executeQuery(sql,rh,id);
		return list.size() == 1 ? list.get(0) : null;
	}

	@Override
	public List<Student> getAll() {
		String sql = "select * from student ";
		return CRUDTemplate.executeQuery(sql,new StuResultSetHandImp());	
	}

	@Override
	public Integer getCount() {
		String sql = "select count(*) as count from student";
		return CRUDTemplate.executeQuery(sql,new StuCountResultSetIml());	
	}
}

class StuResultSetHandImp implements IResultSetHandler<List<Student>> {
	@Override
	public List<Student> handle(ResultSet rs) throws Exception {
		List<Student> list = new ArrayList<Student>();
		while (rs.next()) {
			Student stu = new Student();
			stu.setName(rs.getString("name"));
			stu.setAge(rs.getInt("age"));
			stu.setId(rs.getInt("id"));
			list.add(stu);
		}
		return list;
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









