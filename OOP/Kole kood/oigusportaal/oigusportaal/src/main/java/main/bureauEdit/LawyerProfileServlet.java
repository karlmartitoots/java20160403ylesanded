package main.bureauEdit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.details.AttorneyDetails;
import main.login.UserBean;
import main.search.DBConnection;

public class LawyerProfileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3532116354674784931L;

	AttorneyDetails attorney = new AttorneyDetails();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		int attorneyId = 0;
		// Get attorney's ID from request
		try{
			attorneyId = Integer.parseInt(req.getParameter("attorneyId"));
		}
		catch (NumberFormatException e){
			attorneyId = (int) req.getAttribute("attorneyId");
			e.printStackTrace();
		}
//		System.out.println("Attorney's id: " + attorneyId);

		// Get HTTP session
		HttpSession session = req.getSession(true);

		UserBean user = (UserBean) session.getAttribute("user");
		session.setAttribute("currentSessionUser", user);
		session.setMaxInactiveInterval(3000);

		// Initiate database connection

		DBConnection connect = new DBConnection();

		Connection curConnection = null;

		curConnection = connect.getConnection();

		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = curConnection.createStatement();

			// Select the right attorney from database
			resultSet = statement
					.executeQuery("SELECT * FROM attorney WHERE attorneyid = "
							+ attorneyId + ";");

			// Get the details and put them to attorney variable
			while (resultSet.next()) {
				attorney.setName(resultSet.getString("name"));
				attorney.setEmail(resultSet.getString("email"));
				attorney.setBureauId(resultSet.getInt("bureauid"));
				attorney.setAttorneyId(resultSet.getInt("attorneyid"));				
				attorney.setPicturePath(resultSet.getString("imgpath"));
			}
			attorney.setAttorneyId(attorneyId);
			
//			System.out.println("Attorney email: " + attorney.getEmail());
//			System.out.println("Attorney name: " + attorney.getName());
//			System.out.println("Attorney bureauId: " + attorney.getBureauId());

			// Close connections
			resultSet.close();
			statement.close();
			curConnection.close();

			// Add attributes to request and dispatch the request to
			// LawyerProfile

			req.setAttribute("attorney", attorney);
			req.getRequestDispatcher("LawyerProfile.jsp").forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
