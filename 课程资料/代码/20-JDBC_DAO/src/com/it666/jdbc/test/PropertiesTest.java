package com.it666.jdbc.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertiesTest {
	public static void main(String[] args) throws Exception {
		//读取配置文件
		Properties p = new Properties();
		FileInputStream in = new FileInputStream("resource/db.properties");
		p.load(in);
		//读取文件
		System.out.println(p.getProperty("url"));
		System.out.println(p.getProperty("user"));
	}
	
}
