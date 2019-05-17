package database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	
	public static Connection doConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bengkel2018","root","");
		return conn;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(DBConnection.doConnection());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

