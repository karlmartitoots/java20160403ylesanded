package main.bureauEdit;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.details.SuccessStoryDetails;

public class StoryInitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3422116354674784931L;

	SuccessStoryDetails story = new SuccessStoryDetails();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
			FieldsMaker fields = new FieldsMaker(0, true);
			ArrayList<String> rows = fields.getCheckBoxCode();
			req.setAttribute("fieldRows", rows);
			req.getRequestDispatcher("StoryProfile.jsp").forward(req, resp);
	}

}
