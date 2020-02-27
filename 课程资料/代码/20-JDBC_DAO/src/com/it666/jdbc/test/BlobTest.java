package com.it666.jdbc.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.it666.jdbc.util.JdbcUtil;

public class BlobTest {
	public static void main(String[] args) throws Exception {
		
		//连接数据库
		Connection conn = JdbcUtil.getConn();
		String sql  = "select * from student where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, 1);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			//获取图片
			Blob blob = rs.getBlob("img");
			//获取图片的二进流
			InputStream in = blob.getBinaryStream();
			
			//把程序当中的文件写到磁盘
			Files.copy(in, Paths.get("d:/mysqlIcon.png"));
			
		}
		JdbcUtil.close(conn, ps, rs);
		
	}
	
	
	
	void write() throws Exception {
		Connection conn = JdbcUtil.getConn();
		String sql = "insert into student (img) values(?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		//读取磁盘当中的文件，转成计算机能够识别的二进制
		//InputStream输入流
		//把电脑当中的文件放到程序当中（是以二进流）（输入流InputStream）
		FileInputStream in = new FileInputStream("d:/icon.png");
		ps.setBlob(1, in);
		ps.executeUpdate();
		JdbcUtil.close(conn, ps, null);
	}
}
