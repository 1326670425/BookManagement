package database;

import java.sql.*;

public class Conn {
	public static Connection getConnect(){
		Connection conn = null;
		String driverName = "com.mysql.jdbc.Driver";
		String dbName = "book";
		String dbUserName = "root";
		String dbPassword = "www";
		String URL = "jdbc:mysql://localhost:3306/"+dbName+"?useSSL=true";
		try{
			Class.forName(driverName);
			//System.out.println("驱动加载成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			conn = DriverManager.getConnection(URL,dbUserName,dbPassword);
			//System.out.println("连接成功");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}

}
