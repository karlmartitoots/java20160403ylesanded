package main.search;

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

import main.bureauEdit.NameToValueDecrypter;
import main.details.BureauSearchResults;
import main.details.StorySearch;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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

		ArrayList<BureauSearchResults> bureauSR = new ArrayList<BureauSearchResults>();

		int fieldId = 1;

		
		String sql;
		String sql2;
		String sql3;
		String sql4;

		String county = null;
		int countyId;

		String city = null;
		int cityId;

		String region = null;
		int regionId;

		String averagePrice;
		String SSDateFrom;
		String SSDateTo;
		
		boolean checkBoxDate = false;
		boolean checkBoxPrice = false;

		int ap;

		SSDateFrom = request.getParameter("From");
		request.setAttribute("fromDate", SSDateFrom);
		if (SSDateFrom.isEmpty())
		{
			SSDateFrom = "01-01-1000";
			System.out.println(SSDateFrom);
		}
		else 
		{
			if (SSDateFrom.contains("/")){
				String year = SSDateFrom.substring(6);
				String day = SSDateFrom.substring(3,5);
				String month = SSDateFrom.substring(0,2);
				System.out.println("Aasta: " + year);
				System.out.println("Kuu: " + month);
				System.out.println("Päev: " + day);
				SSDateFrom = year + "-" + month + "-" + day;
			}
			else {
				
			}
		}
		
		SSDateTo = request.getParameter("To");
		request.setAttribute("toDate", SSDateTo);
		if (SSDateTo.isEmpty())
		{
			SSDateTo = "01-01-1010";
			System.out.println(SSDateTo);
		}
		else 
		{
			if (SSDateTo.contains("/")){
				String year = SSDateTo.substring(6);
				String day = SSDateTo.substring(3,5);
				String month = SSDateTo.substring(0,2);
				System.out.println("Aasta: " + year);
				System.out.println("Kuu: " + month);
				System.out.println("Päev: " + day);
				SSDateTo = year + "-" + month + "-" + day;
			}
			else {
				
			}
		}
		
	

		checkBoxDate = request.getParameter("Date") != null;
		checkBoxPrice = request.getParameter("Price") != null;
		if (checkBoxDate){
			request.setAttribute("dateChecked", true);
		}
		if (checkBoxPrice){
			request.setAttribute("priceChecked", true);
		}
		
		
		
		fieldId = Integer.parseInt(request.getParameter("fieldId"));

		cityId = Integer.parseInt(request.getParameter("cities"));
		countyId = Integer.parseInt(request.getParameter("counties"));
		regionId = Integer.parseInt(request.getParameter("regions"));
		
		NameToValueDecrypter names = new NameToValueDecrypter(cityId, regionId, countyId);
		city = names.getCityName();
		county = names.getCountyName();
		region = names.getRegionName();
		request.setAttribute("regionValue", regionId);
		request.setAttribute("cityValue", cityId);
		request.setAttribute("countyValue", countyId);
		

		averagePrice = request.getParameter("averageprice");
		request.setAttribute("averageprice", averagePrice);
		System.out.println(averagePrice);


		ap = Integer.parseInt(averagePrice);

		DBConnection connect = new DBConnection();

		Connection curConnection = null;

		curConnection = connect.getConnection();

		Statement stmt = null;
		ResultSet rs = null;
		
		Statement sstory = null;
		ResultSet rsstory = null;
		
		Statement sfield = null;
		ResultSet rsfield = null;

		sql = "SELECT bureau.bureauid, bureau.name, bureau.email, bureau.averageprice, "
				+ "bureau.street, bureau.postalcode, bureau.phone, bureau.cityname, bureau.image"
				+ " FROM bureau, fieldbureaujunction, successstory "
				+ " Where fieldbureaujunction.bureauid=bureau.bureauid and fieldbureaujunction.fieldid="+ fieldId +""
				+ "and  successstory.bureauid=bureau.bureauid and successstory.date BETWEEN '"
				+ SSDateFrom
				+ "' and '"
				+ SSDateTo
				+ "' "
				+ "and bureau.cityname='"
				+ city
				+ "' and bureau.regionname='"
				+ region
				+ "'"
				+ " and bureau.averageprice BETWEEN '"
				+ 0
				+ "' and '"
				+ ap
				+ "' "
				+ " and bureau.countyname='" + county + "' ;";

		sql2 = "SELECT bureau.bureauid, bureau.name, bureau.email, bureau.averageprice, "
				+ "bureau.street, bureau.postalcode, bureau.phone, bureau.cityname, bureau.image"
				+ " FROM bureau, fieldbureaujunction, successstory "
				+ " Where fieldbureaujunction.bureauid=bureau.bureauid and fieldbureaujunction.fieldid="+ fieldId +""
				+ "and  successstory.bureauid=bureau.bureauid and successstory.date BETWEEN '"
				+ SSDateFrom
				+ "' and '"
				+ SSDateTo
				+ "' "
				+ "and bureau.cityname='"
				+ city
				+ "' and bureau.regionname='"
				+ region + "'" + "  and bureau.countyname='" + county + "' ;";

		sql3 = "SELECT bureau.bureauid, bureau.name, bureau.email, bureau.averageprice, "
				+ "bureau.street, bureau.postalcode, bureau.phone, bureau.cityname, bureau.image"
				+ " FROM bureau, fieldbureaujunction "
				+ " Where fieldbureaujunction.bureauid=bureau.bureauid and fieldbureaujunction.fieldid="+ fieldId +""
				+ " and bureau.cityname='"
				+ city
				+ "' and bureau.regionname='"
				+ region
				+ "'"
				+ "  and bureau.countyname='"
				+ county
				+ "' and bureau.averageprice<=" + ap + " ;";

		sql4 = "SELECT bureau.bureauid, bureau.name, bureau.email, bureau.averageprice, "
				+ "bureau.street, bureau.postalcode, bureau.phone, bureau.cityname, bureau.image"
				+ " FROM bureau, fieldbureaujunction "
				+ " Where fieldbureaujunction.bureauid=bureau.bureauid and fieldbureaujunction.fieldid="+ fieldId +""
				+ " and bureau.cityname='"
				+ city
				+ "' and bureau.regionname='"
				+ region
				+ "'"
				+ "  and bureau.countyname='" + county + "' ;";

		try {
			stmt = curConnection.createStatement();

			if (checkBoxDate  && checkBoxPrice )
				rs = stmt.executeQuery(sql);
			else if (checkBoxDate  && checkBoxPrice )
				rs = stmt.executeQuery(sql2);
			else if (!checkBoxDate  && checkBoxPrice )
				rs = stmt.executeQuery(sql3);
			else if (!checkBoxDate  && !checkBoxPrice )
				rs = stmt.executeQuery(sql4);

			while (rs.next()) {

				BureauSearchResults br = new BureauSearchResults();

				int bureauId = rs.getInt("bureauid");
				String bureauName = rs.getString("name");
				String bureauEmail = rs.getString("email");
				int bureauAveragePrice = rs.getInt("averageprice");
				String bureauStreet = rs.getString("street");
				String bureauPostalcode = rs.getString("postalcode");
				String bureauPhone = rs.getString("phone");
				String bureauCity = rs.getString("cityname");
				String bureauImage = rs.getString("image");
				
				
				
				br.setName(bureauName);
				br.setEmail(bureauEmail);
				br.setAveragePrice(bureauAveragePrice);
				br.setStreet(bureauStreet);
				br.setPostalcode(bureauPostalcode);
				br.setPhoneNumber(bureauPhone);
				br.setCity(bureauCity);
				br.setImage(bureauImage);
				br.setBureauId(bureauId);

				System.out.println("Bureau Id: " + bureauId);
				System.out.println("Bureau Name: " + bureauName);
				System.out.println("Bureau Email: " + bureauEmail);
				System.out.println("Bureau AveragePrice " + bureauAveragePrice);
				System.out.println("Bureau Street " + bureauStreet);
				System.out.println("Bureau Postal " + bureauPostalcode);
				System.out.println("Bureau Phone " + bureauPhone);
				System.out.println("Bureau Cityname " + bureauCity);
				System.out.println("Bureau Image: " + bureauImage);

				System.out
						.println("--------------------------------------------");
				boolean existsAlready = false;
				for (int i = 0; i<bureauSR.size(); i++){
					if (br.getBureauId() == bureauSR.get(i).getBureauId()){
						existsAlready = true;
					}
				}
				if (!existsAlready){
					bureauSR.add(br);
				}

			}

			
			rs.close();
			stmt.close();
			
			for (int i = 0; i < bureauSR.size(); i++) {	
				ArrayList<StorySearch> stories = new ArrayList<StorySearch>();
				sstory = curConnection.createStatement();
				rsstory = sstory
						.executeQuery("SELECT successstory.filepath, successstory.participants, successstory.date FROM successstory WHERE bureauid='"
								+ bureauSR.get(i).getBureauId() + "';");
				while (rsstory.next()){
					StorySearch story = new StorySearch();
					story.setDate(rsstory.getDate("date"));
					story.setFilepath(rsstory.getString("filepath"));
					story.setParticipants(rsstory.getString("participants"));
					stories.add(story);
				}
				StorySearch earliest = null;
				if (stories.size() !=0){
					for (int j=0; i<stories.size(); j++){
						if (stories.get(j).getDate() != null){
							earliest = stories.get(j);
							System.out.println("Earliest date at beginning: " + earliest.getDate().toString());
							break;
						}
					}
					for (int k=0; k<(stories.size()-1); k++){
						try{
						if(stories.get(k+1).getDate().after(stories.get(k).getDate())){
							System.out.println("Date " + stories.get(k).getDate().toString() + " is changed to " + stories.get(k+1).getDate().toString());
							earliest = stories.get(k+1);
						}
						}
						catch (NullPointerException e){
							e.printStackTrace();
						}
					}
					System.out.println("Last participants: " + earliest.getParticipants());
					System.out.println("Last path: " + earliest.getFilepath());
					bureauSR.get(i).setLastStoryParticipants(earliest.getParticipants());
					bureauSR.get(i).setLastStoryPath(earliest.getFilepath());
				}
			}
			
			if (bureauSR.size() != 0){
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
			curConnection.close();
			
			
			request.setAttribute("fieldId", fieldId);
			request.setAttribute("bureauSR", bureauSR);
			request.getRequestDispatcher("CatalogSearchDetailedResults.jsp")
					.forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println("SEARCH ERROR");
			e.printStackTrace();
		}

	}

}