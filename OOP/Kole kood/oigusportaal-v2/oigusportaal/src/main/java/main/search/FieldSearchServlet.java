package main.search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.details.BureauDetails;
import main.details.StorySearch;

/**
 * Servlet implementation class FieldSearchServlet
 */
public class FieldSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FieldSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Welcome to FieldSearchServlet");

				
		int fieldId = Integer.parseInt(request.getParameter("param"));

		

		System.out.println(fieldId);

		ArrayList<BureauDetails> bureau = new ArrayList<BureauDetails>();

		DBConnection connect = new DBConnection();

		Connection curConnection = null;

		curConnection = connect.getConnection();

		Statement stmt = null;
		ResultSet rs = null;

		Statement sstory = null;
		ResultSet rsstory = null;
		
		Statement sfield = null;
		ResultSet rsfield = null;

		try {
			stmt = curConnection.createStatement();
			rs = stmt
					.executeQuery("SELECT bureau.bureauid, bureau.name, bureau.email, bureau.image, bureau.cityname, bureau.averageprice FROM bureau, fieldbureaujunction "
							+ "Where fieldbureaujunction.bureauid=bureau.bureauid and fieldbureaujunction.fieldid="
							+ fieldId + ";");

			while (rs.next()) {

				BureauDetails br = new BureauDetails();

				br.setBureauId(rs.getInt("bureauid"));
				br.setBureauName(rs.getString("name"));
				// br.setRegistryCode(rs.getInt("registrycode"));
				br.setEmail(rs.getString("email"));
				br.setImage(rs.getString("image"));
				br.setCity(rs.getString("cityname"));
				br.setAveragePrice(rs.getInt("averageprice"));				

				// br.setPassword(rs.getString("password"));
				// br.setAveragePrice(rs.getInt("averagePrice"));
				// br.setStreet(rs.getString("street"));
				// br.setPostalcode(rs.getInt("postalcode"));
				// br.setFieldId(rs.getInt("fieldid"));

				bureau.add(br);

				// System.out.println("Bureau Name: " + br.getBureauName());
				// System.out.println("Email: " + br.getEmail());
				// System.out.println("Field: " + br.getFieldName());
				// System.out.println("Image: " + br.getImage());
				/*
				 * System.out.println("Registrycode: " + br.getRegistryCode());
				 * System.out.println("Postcode: " + br.getPostalcode());
				 * System.out.println("Street " + br.getStreet());
				 * System.out.println("Averageprice " + br.getAveragePrice());
				 */

				System.out
						.println("--------------------------------------------");

			}

			

			rs.close();
			stmt.close();
			
			for (int i = 0; i < bureau.size(); i++) {	
				ArrayList<StorySearch> stories = new ArrayList<StorySearch>();
				sstory = curConnection.createStatement();
				rsstory = sstory
						.executeQuery("SELECT successstory.filepath, successstory.participants, successstory.date FROM successstory WHERE bureauid='"
								+ bureau.get(i).getBureauId() + "';");
				while (rsstory.next()){
					StorySearch story = new StorySearch();
					story.setDate(rsstory.getDate("date"));
					story.setFilepath(rsstory.getString("filepath"));
					story.setParticipants(rsstory.getString("participants"));
					stories.add(story);
				}
				StorySearch earliest = null;
				for (int j=0; i<stories.size(); j++){
					if (stories.get(j).getDate() != null){
						earliest = stories.get(j);
						break;
					}
				}
				for (int k=0; k<(stories.size()-1); k++){
					try{
					if(stories.get(k+1).getDate().after(stories.get(k).getDate())){
						earliest = stories.get(k+1);
					}
					}
					catch (NullPointerException e){
						e.printStackTrace();
					}
				}
				if (earliest != null){
					System.out.println("Last participants: " + earliest.getParticipants());
					System.out.println("Last path: " + earliest.getFilepath());
					
					bureau.get(i).setLastStoryParticipants(earliest.getParticipants());
					bureau.get(i).setLastStoryPath(earliest.getFilepath());
				}
				
			}
			if (bureau.size() !=0){
				rsstory.close();
				sstory.close();
			}
			
			sfield = curConnection.createStatement();
			rsfield = sfield.executeQuery("SELECT fieldname FROM field where fieldid='" + fieldId +"';");
			
			while(rsfield.next()){
				request.setAttribute("fieldname", rsfield.getString("fieldname"));
			}
			
			rsfield.close();
			sfield.close();
					
			
			Calendar dateTo = Calendar.getInstance();
			Calendar dateFrom = Calendar.getInstance();  
		    dateFrom.setTime(new Date());  
		    Format f = new SimpleDateFormat("MM/dd/yyyy");
		    dateFrom.add(Calendar.YEAR,-1);  
		    System.out.println(f.format(dateFrom.getTime())); 
			curConnection.close();
			request.setAttribute("dateFrom", f.format(dateFrom.getTime()));
			request.setAttribute("dateTo", f.format(dateTo.getTime()));
			request.setAttribute("fieldId", fieldId);
			request.setAttribute("bureau", bureau);
			request.getRequestDispatcher("CatalogSearch.jsp").forward(request,
					response);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}