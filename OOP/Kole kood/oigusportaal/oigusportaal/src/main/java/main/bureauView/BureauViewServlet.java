package main.bureauView;

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
import main.details.AttorneyDetails;
import main.details.SuccessStoryDetails;
import main.login.UserBean;
import main.search.DBConnection;


public class BureauViewServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BureauViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				
		
		ArrayList<AttorneyDetails> attorneys = new ArrayList<AttorneyDetails>();
		ArrayList<SuccessStoryDetails> successStories = new ArrayList<SuccessStoryDetails>();
		ArrayList<String> fields = new ArrayList<String>();
		
        
        DBConnection connect = new DBConnection();

		Connection curConnection = null;

		curConnection = connect.getConnection();
		

		Statement statementUser = null;
		ResultSet resultSetUser = null;
		
		Statement statementAttorneys = null;
		ResultSet resultSetAttorneys = null;
		
		Statement statementStories = null;
		ResultSet resultSetStories = null;
		
		Statement statementFields = null;
		ResultSet resultSetFields = null;
		
		Statement statementFieldName = null;
		ResultSet resultSetFieldName = null;
		
		UserBean user = new UserBean();
		
			
		int bureauId = Integer.parseInt(request.getParameter("bureauId"));
		
		
		
		
		try {
			statementUser = curConnection.createStatement();
			resultSetUser = statementUser.executeQuery("SELECT * FROM bureau WHERE bureauid='" + bureauId + "';" );
			
			
			
			while (resultSetUser.next()){
			user.setAveragePrice(resultSetUser.getInt("averageprice"));
			user.setStreet(resultSetUser.getString("street"));
			user.setBureauName(resultSetUser.getString("name"));
			user.setCategory(resultSetUser.getString("category"));
			user.setEmail(resultSetUser.getString("email"));
			user.setImage(resultSetUser.getString("image"));
			user.setPhone(resultSetUser.getInt("phone"));
			user.setPostalcode(resultSetUser.getInt("postalcode"));
			user.setRegistryCode(resultSetUser.getInt("registrycode"));
			bureauId = (Integer) resultSetUser.getInt("bureauid");
			user.setRegionName(resultSetUser.getString("regionname"));
			user.setCountyName(resultSetUser.getString("countyname"));
			user.setCityName(resultSetUser.getString("cityname"));
			System.out.println("User attributes are set");
			System.out.println("Bureau name: " + user.getBureauName());
			
			}
			
			
			resultSetUser.close();
			statementUser.close();
			
			
			
			statementAttorneys = curConnection.createStatement();
			resultSetAttorneys = statementAttorneys.executeQuery("SELECT * FROM attorney WHERE bureauid='" + bureauId + "';");
			
		
			while (resultSetAttorneys.next()){
				
				AttorneyDetails attorney = new AttorneyDetails();
				
				attorney.setName(resultSetAttorneys.getString("name"));
				attorney.setEmail(resultSetAttorneys.getString("email"));
				attorney.setAttorneyId(resultSetAttorneys.getInt("attorneyid"));
				attorney.setPicturePath(resultSetAttorneys.getString("imgpath"));
				
				attorneys.add(attorney);
				
			}
			
			statementAttorneys.close();
			resultSetAttorneys.close();
			
			statementFields = curConnection.createStatement();
			resultSetFields = statementFields.executeQuery("SELECT field.fieldname FROM field, fieldbureaujunction WHERE field.fieldid = fieldbureaujunction.fieldid AND"
					+ " fieldbureaujunction.bureauid = '" + bureauId + "';");
			
			while (resultSetFields.next()){				
				fields.add(resultSetFields.getString("fieldname"));				
			}
			
			statementFields.close();
			resultSetFields.close();
			
			statementStories = curConnection.createStatement();
			resultSetStories = statementStories.executeQuery("SELECT * FROM successstory WHERE bureauid='" + bureauId + "';");
			
			while (resultSetStories.next()){
				SuccessStoryDetails story = new SuccessStoryDetails();
				
				story.setConclusion(resultSetStories.getString("conclusion"));
				story.setFieldId(resultSetStories.getInt("fieldid"));
				story.setParticipants(resultSetStories.getString("participants"));
				story.setReference(resultSetStories.getString("reference"));
				story.setSuccessStoryId(resultSetStories.getInt("ssid"));
				story.setDate(resultSetStories.getDate("date"));
				story.setFilepath(resultSetStories.getString("filepath"));
				
				successStories.add(story);
			}
			
			statementStories.close();
			resultSetStories.close();
			
			
			
			for (int i = 0; i<successStories.size(); i++){
				statementFieldName = curConnection.createStatement();
				resultSetFieldName = statementFieldName.executeQuery("SELECT fieldname FROM field WHERE fieldid='" + successStories.get(i).getFieldId() + "';");
				while (resultSetFieldName.next()){
					successStories.get(i).setFieldName(resultSetFieldName.getString("fieldname"));
				}
			}
			
			
			curConnection.close();
			
			request.setAttribute("fields", fields);
			request.setAttribute("user", user);
			request.setAttribute("attorneys", attorneys);
			request.setAttribute("successStories", successStories);
			request.getRequestDispatcher("BureauView.jsp").forward(request, resp);
			
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); }
		
		
	}

}
