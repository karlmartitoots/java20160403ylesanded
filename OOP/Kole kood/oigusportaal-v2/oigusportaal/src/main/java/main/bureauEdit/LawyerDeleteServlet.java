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

import main.login.UserBean;
import main.search.DBConnection;

public class LawyerDeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8493352497025095811L;

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
				
				// Initiate database connection

				DBConnection connect = new DBConnection();

				Connection curConnection = null;

				curConnection = connect.getConnection();

				PreparedStatement statement = null;
				
				try{
					String sql = "DELETE FROM attorney WHERE attorneyid='" + attorneyId + "';";
					statement = curConnection.prepareStatement(sql);
					statement.executeUpdate();
					
					req.getRequestDispatcher("BureauProfileServlet").forward(req, resp);
					
					statement.close();
					curConnection.close();
				}
				catch (SQLException e){
					e.printStackTrace();
				}
				
		
		
	}

	
}
