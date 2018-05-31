package main;

import java.sql.*;
import database.QueryUpdate;

public class CreateTable {
	public Object[][] createTable(String sql){
		
		QueryUpdate qu = new QueryUpdate();
		ResultSet rs = null;
		Object[][] list = null;
		
		int count = 0;
		try{
			rs = qu.executeQuery(sql);
			if(!rs.next())
				return null;
			rs.previous();
			while(rs.next())
				count++;

			
			rs.first();
			int colNumber = rs.getMetaData().getColumnCount();
			list = new Object[count][colNumber];
			
			for(int i=0;i<count;i++){

				list[i] = new Object[colNumber];
				for(int j=0;j<colNumber;j++)
					list[i][j] = rs.getString(j+1);
				rs.next();
			}

			rs.close();
			qu.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

}
