package com.mycomp.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mycomp.jdbc.dao.IStudentDao;
import com.mycomp.jdbc.domain.Student;
import com.mycomp.jdbc.util.JDBCUtil;

public class StudentDaoImpl implements IStudentDao {

    @Override
    public void save(Student student) {
        String sql = "insert into student(name, age) values (?,?)";
        QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
        try {
            qr.update(sql, student.getName(), student.getAge());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, Student student) {
        String sql = "update student set name = ?, age = ? where id = ?";
        QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
        try {
            qr.update(sql, student.getName(), student.getAge(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from student where id = ?";
        QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
        try {
            qr.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student get(int id) {
        String sql = "select * from student where id = ?";
        QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
        try {
            return qr.query(sql, new BeanHandler<Student>(Student.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        String sql = "select * from student";
        QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<Student>(Student.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getCount() {
        String sql = "select count(*) as count from student";
        QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
        try {
            return ((Long) qr.query(sql, new ScalarHandler(1))).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
