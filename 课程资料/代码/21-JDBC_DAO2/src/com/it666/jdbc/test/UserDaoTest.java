package com.it666.jdbc.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.it666.jdbc.dao.IUserDao;
import com.it666.jdbc.dao.impl.UserDaoImpl;

public class UserDaoTest {
	@Test
	public void getAll() {
//		IUserDao dao = new UserDaoImpl();
//		System.out.println(dao.getAll());
		
		List list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		
		Iterator it = list.iterator();
		while(it.hasNext()) {
			String str = (String)it.next();
			System.out.println(str);
			if(str.equals("2")) {
				list.remove(str);
			}
		}
		System.out.println(list);
	}
}
