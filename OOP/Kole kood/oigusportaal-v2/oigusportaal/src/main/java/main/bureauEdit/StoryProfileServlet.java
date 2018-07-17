package main.bureauEdit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.details.SuccessStoryDetails;
import main.login.UserBean;
import main.search.DBConnection;

public class StoryProfileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3522116354674784931L;

	SuccessStoryDetails story = new SuccessStoryDetails();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		// Get story's ID from request
		int storyId = 0;
		boolean gotStory = false;
		try{
			storyId = Integer.parseInt(req.getParameter("storyId"));
			
		}
		catch(NumberFormatException e){
			e.printStackTrace();
			try{
				storyId = (Integer) req.getAttribute("storyId");
			}
			catch (NullPointerException i){
				i.printStackTrace();
				gotStory = true;
				story = (SuccessStoryDetails) req.getAttribute("story");
			}
			
		}

//			System.out.println("Story's id: " + storyId);

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
			if (!gotStory){
			statement = curConnection.createStatement();

			// Select the right successStory from database
			resultSet = statement
					.executeQuery("SELECT * FROM successstory WHERE ssid = "
							+ storyId + ";");

			// Get the details and put them to story variable
			while (resultSet.next()) {
				story.setSuccessStoryId(resultSet.getInt("ssid"));
				story.setParticipants(resultSet.getString("participants"));
				story.setDate(resultSet.getDate("date"));
				story.setReference(resultSet.getString("reference"));
				story.setConclusion(resultSet.getString("conclusion"));
				story.setBureauId(resultSet.getInt("bureauid"));
				story.setFieldId(resultSet.getInt("fieldid"));
				story.setFilepath(resultSet.getString("filepath"));
			}
			// Close connections
			resultSet.close();
			statement.close();
			curConnection.close();
			}

			
			
			// Get the radio buttons
			FieldsMaker fields = new FieldsMaker(story.getSuccessStoryId(), true);
			ArrayList<String> radios = fields.getCheckBoxCode();

			// Add attributes to request and dispatch the request to
			// StoryProfile
			req.setAttribute("fieldRows", radios);
			req.setAttribute("story", story);
			
			req.getRequestDispatcher("StoryProfile.jsp").forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
