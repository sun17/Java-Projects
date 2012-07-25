package com.web.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import java.sql.Blob;


public class JDBCTest {

	private static final String DataBase_URL = "jdbc:mysql://localhost/";
	public static void main(String[] args) throws IOException{
		Connection connection;
		Statement statement;
		ResultSet resultset;
		
		String db="mytestdb";
		String username = "root";
		String password = "";

		try {
			try {
				Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			connection = DriverManager.getConnection(DataBase_URL+db, username, password);
		
			statement = connection.createStatement();
		
					/*
					String sql = "insert into blobtest(username, photo) values(?, ?)";
					PreparedStatement pstmt = connection.prepareStatement(sql); 
					
					File file = new File("/usr/local/tomcat7/lib/tomcat-dbcp.jar"); 
					FileInputStream fis = new FileInputStream(file); 
					
					pstmt.setString(1, "LiangZhao"); //set parameter 1
					pstmt.setBinaryStream(2, fis, (int)file.length());
					pstmt.executeUpdate();
					
					pstmt.close(); 
					fis.close(); 
					*/
					
					// reading
					
					String sql = "select photo from blobtest where username = ?"; 
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setString(1, "LiangZhao");
					ResultSet rs = pstmt.executeQuery();
					rs.next();
					
					Blob blob = rs.getBlob("photo");
					
					
					//ImageIcon icon = new ImageIcon(blob.getBytes(1, (int)blob.length()));
					//JLabel photo = new JLabel(icon);
					//System.out.println(photo);		
					
					byte[] b = blob.getBytes(1, (int)blob.length());
					
					FileOutputStream fout = new FileOutputStream("/tmp/monkey.jar");
					
					//PrintWriter out = new PrintWriter(new File("sdf"));
				
					fout.write(b);					
					fout.close();
					
					File f = new File("/tmp/monkey.jar");
					System.out.println(f.length()/1024);
					
					rs.close();
					pstmt.close();
					System.out.println("copying done!");
					
				} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}