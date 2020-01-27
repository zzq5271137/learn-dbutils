package com.mycomp.jdbc.test;

import com.mycomp.jdbc.dao.IStudentDao;
import com.mycomp.jdbc.dao.impl.StudentDaoImpl;
import com.mycomp.jdbc.domain.Student;

public class StudentTest {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("米斯塔23333");
        student.setAge(20);
        IStudentDao studentDao = new StudentDaoImpl();
        System.out.println(studentDao.getCount());
    }
}
