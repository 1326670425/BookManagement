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
			//System.out.println("�������سɹ�");
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			conn = DriverManager.getConnection(URL,dbUserName,dbPassword);
			//System.out.println("���ӳɹ�");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}

}
