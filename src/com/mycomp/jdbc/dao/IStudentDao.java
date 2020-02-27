package com.mycomp.jdbc.dao;

import java.util.List;

import com.mycomp.jdbc.domain.Student;

public interface IStudentDao {

    void save(Student student);

    void update(Integer id, Student student);

    void delete(int id);

    Student get(int id);

    List<Student> getAll();
    
    Integer getCount();
    
}
