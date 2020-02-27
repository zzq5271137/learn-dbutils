package com.it666.jdbc.dao;

import java.util.List;

import com.it666.jdbc.domain.User;

public interface IUserDao {
	void save(User u);
	
	List getAll();
}
