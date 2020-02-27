package com.it666.jdbc.dao;

public class Test2 {
	public static void main(String[] args) {

		Student stu = new Student();
		stu.setId(2);
		stu.setName("Â³°à");
		stu.setAge(10);
		StudentDao dao = new StudentDao();
		dao.save(stu);
	}
}
