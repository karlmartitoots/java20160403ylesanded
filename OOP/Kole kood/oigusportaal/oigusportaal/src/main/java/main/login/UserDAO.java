package main.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



import main.search.DBConnection;

public class UserDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	static int active = 1;

	public static UserBean login(UserBean bean) {

		// preparing some objects for connection
		Statement stmt = null;
		DBConnection connect = new DBConnection();

		String email = bean.getEmail();
		String password = bean.getPassword();
		String category = bean.getCategory();
		
		System.out.println("Email is : " + email);
		System.out.println("Password is : " + password);
		System.out.println("Category is : " + category);

		String credentialQuery = "SELECT email, password, category, active FROM bureau "
				+ "Where email='" + email + "' " + "and password='" + password
				+ "' and category='" + category + "' and active= " + active +";";

		
		

		try {

			currentCon = connect.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(credentialQuery);
			System.out.println(credentialQuery);
			boolean more = rs.next();	
			System.out.println(more);

			// if user does not exist set the isValid variable to false
			if (!more) {
				System.out.println("Sorry, you are not a registered user! "
						+ " Please enter your information correctly");

				bean.setValid(false);
			}

			// if user exists set the isValid variable to true
			else {

				bean.setValid(true);
				if (bean.getCategory().equals(1))
					System.out.println("Welcome Admin");
				else if (bean.getCategory().equals(2))
					System.out.println("Welcome Bureau");
			}
			
			System.out.println("Jõudsin siia..");
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}

		// some exception handling
		finally {
			if (rs != null) {
				try {
					System.out.println("DB shutdown");					
					rs.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				rs = null;

			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				currentCon = null;
			}
		}

		return bean;

	}

}