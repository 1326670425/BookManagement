package database;

import java.sql.*;

public class QueryUpdate {
	Statement stmt = null;
	Connection conn = null;
	public QueryUpdate(){
		this.conn = Conn.getConnect();
	}
	public void executeUpdate(String sql){
		try{
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public ResultSet executeQuery(String sql){
		ResultSet rs = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	public void close(){
		try{
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
