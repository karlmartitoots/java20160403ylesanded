package main.bureauEdit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.details.AttorneyDetails;
import main.login.UserBean;
import main.search.DBConnection;

public class LawyerEditServlet extends HttpServlet {

	private static final long serialVersionUID = 3749700411746807213L;
	/**
	 * 
	 */

	AttorneyDetails attorney = new AttorneyDetails();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		// Get attorney's ID from request
		int attorneyId = Integer.parseInt(req.getParameter("attorneyId"));

		// Get HTTP session
		HttpSession session = req.getSession(true);

		UserBean user = (UserBean) session.getAttribute("user");
		session.setAttribute("currentSessionUser", user);
		session.setMaxInactiveInterval(3000);

		// Get new details
		String newName = req.getParameter("newName");
		String newEmail = req.getParameter("newEmail");

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
				// Later
				// attorney.setPicturePath(resultSet.getString("imgpath"));
			}

			// Close all connections BUT database connection
			resultSet.close();
			statement.close();

			// Set booleans if things have changed or not
			boolean nameChanged = false;
			boolean emailChanged = false;

			// See if email or name have changed
			if (!attorney.getName().equals(newName)) {
				nameChanged = true;
				attorney.setName(newName);
			}
			if (!attorney.getEmail().equals(newEmail)) {
				emailChanged = true;
				attorney.setEmail(newEmail);
			}
			// Initiate prepared statement and update (if needed)
			if (nameChanged || emailChanged) {
				PreparedStatement prepsmt = null;
				String sql = "UPDATE attorney SET ";
				if (nameChanged)
					sql = sql.concat("name='" + newName + "', ");
				if (emailChanged)
					sql = sql.concat("email='" + newEmail + "'");
				if (sql.endsWith(", ")) {
					sql = sql.substring(0, sql.length() - 2);
				}
				sql = sql.concat(" WHERE attorneyid='" + attorneyId + "';");

				prepsmt = curConnection.prepareStatement(sql);
				prepsmt.executeUpdate();
			}
			curConnection.close();

			// Add attributes to request and dispatch the request to
			// LawyerProfile

			req.setAttribute("attorney", attorney);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/BureauProfileServlet");
			rd.forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
