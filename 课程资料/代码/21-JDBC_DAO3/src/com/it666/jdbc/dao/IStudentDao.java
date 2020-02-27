package com.it666.jdbc.dao;

import java.util.List;

import com.it666.jdbc.domain.Student;

public interface IStudentDao {
	/**
	 * 保存一个学生
	 */
	public void save(Student stu);
	/**
	 * 删除学生
	 */
	public void delete(int id);
	/**
	 * 更新一个学生信息
	 */
	public void update(int id,Student stu);
	/**
	 * 获取指定学生
	 */
	public Student get(int id);
	/**
	 * 获取所有的学生
	 */
	public List<Student> getAll();
	
	/**
	 * 获取学生的总数
	 */
	Integer getCount();
	
	public void test();
	
	
	
}
