package main.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.search.DBConnection;
import main.details.BureauDetails;

/**
 * Servlet implementation class DeleteBureauServlet
 */
public class DeleteBureauServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBureauServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int category = 2;
		
		String sqlDelete;
		String sqlSelect;
		
		String bureauEmail;
		String bureauId;
		
		bureauEmail = request.getParameter("param");
		bureauId = request.getParameter("paramTwo");
		
		System.out.println(bureauEmail);
		System.out.println(bureauId);
		
		
		sqlDelete= " DELETE from fieldbureaujunction Where bureauid=" + bureauId + "; "
				+ "Delete from attorney where bureauid=" + bureauId + ";"
				+ "Delete from successstory where bureauid=" + bureauId + ";"
				+ "DELETE FROM bureau  WHERE email = '" + bureauEmail + "' ;" ;
		
		sqlSelect ="SELECT  bureauid, name, email, active from bureau where category='" + category + "';" ;
	
		ArrayList<BureauDetails> bureau = new ArrayList<BureauDetails>();
		
		DBConnection connect = new DBConnection();

		Connection curConnection = null;

		curConnection = connect.getConnection();
		

		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			stmt = curConnection.createStatement();
			stmt.executeUpdate(sqlDelete);
			
			
			
			
			
			
			rs = stmt
					.executeQuery(sqlSelect);

			while (rs.next()) {

				BureauDetails br = new BureauDetails();
				
				br.setBureauId(rs.getInt("bureauid"));
				br.setBureauName(rs.getString("name"));
				br.setEmail(rs.getString("email"));
				br.setActive(rs.getInt("active"));
				
				
				//br.setPassword(rs.getString("password"));
				//br.setAveragePrice(rs.getInt("averagePrice"));
				//br.setStreet(rs.getString("street"));
				//br.setPostalcode(rs.getInt("postalcode"));
				//br.setFieldId(rs.getInt("fieldid"));
				
				
				bureau.add(br);
				
				System.out.println("Bureau Id: " + br.getBureauId());
				System.out.println("Bureau Name: " + br.getBureauName());
				System.out.println("Email: " + br.getEmail());
				System.out.println("Active: " +br.getActive());
				
				/*System.out.println("Registrycode: " + br.getRegistryCode());
				System.out.println("Postcode: " + br.getPostalcode());
				System.out.println("Street " + br.getStreet());
				System.out.println("Averageprice " + br.getAveragePrice());*/
				
				System.out.println("--------------------------------------------");

			}
			
			
			System.out.println(bureau.get(0).getBureauName());
			
			
			request.setAttribute("bureau", bureau);
			request.getRequestDispatcher("Admin.jsp").forward(request, response);

			rs.close();
			stmt.close();
			curConnection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
