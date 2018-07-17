package main.bureauEdit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.details.AttorneyDetails;
import main.login.UserBean;
import main.search.DBConnection;

public class LawyerAddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8493362497025095811L;

	AttorneyDetails attorney = new AttorneyDetails();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		// Get attributes
		String name = req.getParameter("newName");
		String email = req.getParameter("newEmail");
		int bureauId = Integer.parseInt(req.getParameter("bureauId"));
//		System.out.println("New lawyer name:" + name);

		// Get HTTP session
		HttpSession session = req.getSession(true);

		UserBean user = (UserBean) session.getAttribute("user");
		session.setAttribute("currentSessionUser", user);
		session.setMaxInactiveInterval(3000);

		// Initiate database connection

		DBConnection connect = new DBConnection();

		Connection curConnection = null;

		curConnection = connect.getConnection();

		PreparedStatement statement = null;

		try {
			String sql = "INSERT INTO attorney (name, email, bureauid) VALUES ('"
					+ name + "','" + email + "'," + bureauId + ")";
			statement = curConnection.prepareStatement(sql);
			statement.executeUpdate();

			attorney.setBureauId(bureauId);
			attorney.setName(name);
			attorney.setEmail(email);

			

			// Add attributes to request and dispatch the request to
			// LawyerProfile

			req.setAttribute("attorney", attorney);
			req.getRequestDispatcher("BureauProfileServlet").forward(req, resp);
			
			statement.close();
			curConnection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
