package main.bureauEdit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
import main.details.SuccessStoryDetails;
import main.login.UserBean;
import main.search.DBConnection;

public class StoryEditServlet extends HttpServlet {

	private static final long serialVersionUID = 3748700411746807213L;
	/**
	 * 
	 */

	SuccessStoryDetails story = new SuccessStoryDetails();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		// Get story's ID from request		
		int storyId = Integer.parseInt(req.getParameter("storyId"));

		// Get HTTP session
		HttpSession session = req.getSession(true);

		UserBean user = (UserBean) session.getAttribute("user");
		session.setAttribute("currentSessionUser", user);
		session.setMaxInactiveInterval(3000);

		// Get new details
		String participants = req.getParameter("newParticipants");
		String reference = req.getParameter("newReference");
		String date = req.getParameter("newDate");
		int field = Integer.parseInt(req.getParameter("fields"));
		System.out.println("Selline ta näeb välja: " + date);
		String dateFix = "";
		if (date.contains("/")){
			String year = date.substring(6);
			String day = date.substring(3,5);
			String month = date.substring(0,2);
			System.out.println("Aasta: " + year);
			System.out.println("Kuu: " + month);
			System.out.println("Päev: " + day);
			dateFix = year + "-" + month + "-" + day;
		}
		else {
			dateFix = date;
		}
//		Date date = java.sql.Date.valueOf(req.getParameter("newDate"));
		String conclusion = req.getParameter("newConclusion");
		boolean tooLong = false;
		if(conclusion.length() > 300){
			tooLong = true;
		}
		
		conclusion = conclusion.replaceAll("'", "\"");
		
		story.setSuccessStoryId(storyId);
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
					.executeQuery("SELECT * FROM successstory WHERE ssid = "
							+ storyId + ";");

			// Get the details and put them to attorney variable
			while (resultSet.next()) {
				story.setConclusion(resultSet.getString("conclusion"));
				story.setDate(resultSet.getDate("date"));
				story.setFieldId(resultSet.getInt("fieldid"));
				story.setParticipants(resultSet.getString("participants"));
				story.setReference(resultSet.getString("reference"));
				story.setBureauId(resultSet.getInt("bureauId"));
								
			}

			// Close all connections BUT database connection
			resultSet.close();
			statement.close();

			// Set booleans if things have changed or not
			boolean conclusionChanged = false;
			boolean dateChanged = false;
			boolean fieldIdChanged = false;
			boolean participantsChanged = false;
			boolean referenceChanged = false;

			// See if any of the values changed
			if (!story.getConclusion().equals(conclusion) && !tooLong){
				conclusionChanged = true;
				story.setConclusion(conclusion);
			}
			if (story.getFieldId() != field){
				fieldIdChanged = true;
				story.setFieldId(field);
			}
			
			if (!story.getDate().equals(date)){
				dateChanged = true;
				story.setDate(java.sql.Date.valueOf(dateFix));
			}
			
						
			if (!story.getParticipants().equals(participants)){
				participantsChanged = true;
				story.setParticipants(participants);
			}
			
			if (!story.getReference().equals(reference)){
				referenceChanged = true;
				story.setReference(reference);
			}
			// Initiate prepared statement and update (if needed)
			if (conclusionChanged || dateChanged || fieldIdChanged || participantsChanged || referenceChanged) {
				PreparedStatement prepsmt = null;
				String sql = "UPDATE successstory SET ";
				if (conclusionChanged)
					sql = sql.concat("conclusion='" + conclusion + "', ");
				if (dateChanged)
					sql = sql.concat("date='" + date + "', ");				
				if (participantsChanged)
					sql = sql.concat("participants='" + participants + "', ");
				if (referenceChanged)
					sql = sql.concat("reference='" + reference + "', ");
				if (fieldIdChanged)
					sql = sql.concat("fieldid='" + field + "', ");
				if (sql.endsWith(", ")) {
					sql = sql.substring(0, sql.length() - 2);
				}
				sql = sql.concat(" WHERE ssid='" + storyId + "';");

				prepsmt = curConnection.prepareStatement(sql);
				prepsmt.executeUpdate();
			}
			curConnection.close();

			// Add attributes to request and dispatch the request to
			// Story
			
			req.setAttribute("story", story);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/BureauProfileServlet");
			rd.forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
