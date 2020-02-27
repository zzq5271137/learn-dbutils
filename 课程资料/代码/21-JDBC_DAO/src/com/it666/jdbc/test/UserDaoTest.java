package com.it666.jdbc.test;

import org.junit.Test;

import com.it666.jdbc.dao.IUserDao;
import com.it666.jdbc.dao.impl.UserDaoImpl;

public class UserDaoTest {
	@Test
	public void getAll() {
		IUserDao dao = new UserDaoImpl();
		System.out.println(dao.getAll());
	}
}
