package main.bureauActivation;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.search.DBConnection;

public class ActivationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActivationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("mail_address");
		String hash = req.getParameter("hash");

		DBConnection connection = new DBConnection();

		Connection curconnection = null;

		curconnection = connection.getConnection();

		Statement stmt = null;
		ResultSet rs = null;

		String contextpath = req.getContextPath();

		try {

			stmt = curconnection.createStatement();

			rs = stmt
					.executeQuery("SELECT email, hash FROM bureau WHERE email = '"
							+ email + "' AND hash = '" + hash + "';");

			if (rs.next()) {
				stmt.executeUpdate("UPDATE bureau SET active = '1' WHERE hash = '" + hash + "';");
				resp.sendRedirect(contextpath + "/ActivationSuccess.jsp");
			} else {
				resp.sendRedirect(contextpath + "/ActivationFailed.jsp");
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

}
