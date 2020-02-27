package com.it666.jdbc.test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import com.it666.jdbc.domain.User;

class ClassTest{
	public Class c;
	public ClassTest(Class c) {
		this.c = c;
	}
}

public class Test {
	
	public static void main(String[] args) throws Exception {
		
	/*	ClassTest ct =  new ClassTest(User.class);
		User u = (User) ct.c.newInstance();*/
		User u = User.class.newInstance();
		//获取指定字节码的属性信息
		BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Object.class);
		//获取所有的属性描述器
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			//获取所有属性的名称
			//System.out.println(pd.getName());
			//获取get方法
			///System.out.println(pd.getReadMethod());
			System.out.println(pd.getWriteMethod());
			pd.getWriteMethod().invoke(u, "111");
		}
		System.out.println(u.getName());
		System.out.println(u.getPwd());
		
	}
}
