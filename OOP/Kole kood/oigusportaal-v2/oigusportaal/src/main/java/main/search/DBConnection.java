package main.search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




	public class DBConnection {
		 
	
	 public Connection getConnection()  {
			
//		 System.out.println("-------- H2 "
//					+ "JDBC Connection Testing ------------");
	 
			try {
	 
				Class.forName("org.h2.Driver");
	 
			} catch (ClassNotFoundException e) {
	 
				System.out.println("Where is your H2 JDBC Driver? "
						+ "Include in your library path!");
				e.printStackTrace();
				return null;
	 
			}
	 
//			System.out.println("H2 JDBC Driver Registered!");
	 
			Connection connection = null;
	 
			try {
	 
				connection = DriverManager.getConnection(
						"jdbc:h2:~/oigusportaal");
	 
			} catch (SQLException e) {
	 
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return connection;
	 
			}
	 
			if (connection != null) {
//				System.out.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
			}
			return connection;
			
	}
	 
	}
