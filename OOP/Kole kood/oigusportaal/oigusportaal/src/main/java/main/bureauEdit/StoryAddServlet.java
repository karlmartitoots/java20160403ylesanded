package main.bureauEdit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.details.SuccessStoryDetails;
import main.login.UserBean;
import main.search.DBConnection;

public class StoryAddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8393362497025095811L;

	SuccessStoryDetails story = new SuccessStoryDetails();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		// Get attributes
		String participants = req.getParameter("newParticipants");
		String reference = req.getParameter("newReference");
		String date = req.getParameter("newDate");
		int field = Integer.parseInt(req.getParameter("fields"));
		String year = date.substring(6);
		String day = date.substring(3,5);
		String month = date.substring(0,2);
		String dateFix = year + "-" + month + "-" + day;
		Date realDate = java.sql.Date.valueOf(dateFix);
		String conclusion = req.getParameter("newConclusion");
		int bureauId = Integer.valueOf(req.getParameter("bureauId"));		

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
		
		Statement smt = null;
		ResultSet rst  =null;

		try {
			String sql = "INSERT INTO successstory (participants, reference, date, conclusion, bureauid, fieldid) VALUES ('"
					+ participants + "','" + reference + "','" + realDate + "','" + conclusion + "','" + bureauId + "','" + field + "');";
			statement = curConnection.prepareStatement(sql);
//			System.out.println(statement);
			statement.executeUpdate();

			story.setBureauId(bureauId);
			story.setConclusion(conclusion);
			story.setDate(realDate);
			story.setParticipants(participants);
			story.setReference(reference);
			story.setFieldId(field);
			
			statement.close();
			// Get the new successtory id

			smt = curConnection.createStatement();
			rst = smt.executeQuery("SELECT ssid FROM successstory WHERE reference='" + reference + "';");
			while (rst.next()){
				story.setSuccessStoryId(rst.getInt("ssid"));
			}
			// Add attributes to request and dispatch the request to
			// LawyerProfile 

						
			rst.close();
			smt.close();
			curConnection.close();
			
			req.setAttribute("story", story);
			req.getRequestDispatcher("StoryProfileServlet").forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
