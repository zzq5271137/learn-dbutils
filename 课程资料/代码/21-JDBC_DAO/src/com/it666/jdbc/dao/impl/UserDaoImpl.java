package com.it666.jdbc.dao.impl;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.it666.jdbc.dao.IUserDao;
import com.it666.jdbc.domain.Student;
import com.it666.jdbc.domain.User;
import com.it666.jdbc.handler.IResultSetHandler;
import com.it666.jdbc.util.CRUDTemplate;

public class UserDaoImpl implements IUserDao{
	
	@Override
	public void save(User u) {
		String sql = "insert into User(name,pwd) values(?,?)";
		CRUDTemplate.executeUpdate(sql, u.getName(),u.getPwd());
	}

	@Override
	public List<User> getAll() {
		String sql = "select * from user";
		return  CRUDTemplate.executeQuery(sql, new UserResultSetImp());
	}
}

class UserResultSetImp implements IResultSetHandler<List<User>>{
	@Override
	public List<User> handle(ResultSet rs) throws Exception {
		List<User> list = new ArrayList<>();
		while (rs.next()) {
			User u = new User();
			u.setName(rs.getString("name"));
			u.setPwd(rs.getString("pwd"));
			list.add(u);
		}
		return list;
	}
}








