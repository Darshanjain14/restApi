package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnect {
	
	public static Connection conn;
	public static Connection getConnection()
	{
		try {
		if(conn==null)
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zversal","darshan","Darshan@1409");
			
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println(conn);
		return conn;
	}

}
