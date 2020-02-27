package com.it666.jdbc.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements IResultSetHandler<List<T>>{

	private Class<T> classType;
	public BeanListHandler(Class<T> classType) {
		this.classType = classType;
	}
	
	@Override
	public List<T> handle(ResultSet rs) throws Exception {
		List<T> list = new ArrayList<>();
		while(rs.next()) {
			//创建一个对象
			T obj = this.classType.newInstance();
			//通过内省来拿属性
			BeanInfo bf = Introspector.getBeanInfo(this.classType,Object.class);
			//获取所有属性描述器
			PropertyDescriptor[] pds = bf.getPropertyDescriptors();
			//遍历每一个属性的描述 器
			for (PropertyDescriptor pd : pds) {
				Object val = rs.getObject(pd.getName());
				//给对象设置属性值
				pd.getWriteMethod().invoke(obj, val);
			}
			//把对象存放 到集合当中
			list.add(obj);
		}
		
		return list;
	}

}
