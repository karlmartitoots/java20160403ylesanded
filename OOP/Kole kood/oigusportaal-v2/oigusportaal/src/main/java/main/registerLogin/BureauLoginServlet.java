package main.registerLogin;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.search.DBConnection;

/**
 * Servlet implementation class BureauLoginServlet
 */
public class BureauLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BureauLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		DBConnection connection = new DBConnection();
		
		Connection curconnection = null;
		
		curconnection = connection.getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String contextpath = request.getContextPath();
		
		try{
			
			stmt = curconnection.createStatement();
			
			rs = stmt.executeQuery("Select email, password from bureau "
					+ "where email ='"+email
					+"' and password ='"+password+"'");
					/*+"' and password = ' "+password+"'");*/
			
			/*rs = stmt.executeQuery("Select * from users");*/
			
			/*String c_email = rs.getString("email");
			
			String c_password = rs.getString("password");
			System.out.println(c_email);
			System.out.println(c_password);*/ 
			/*System.out.println("EMAIL: "+rs.getString("email"));
			System.out.println("PASSWORD: "+rs.getString("password"));*/
			
			if(rs.next()){
				/*if (email.equals(rs.getString("email"))){
					System.out.println("SIIN SIIN SIIN: "+rs.getString("email")+" "+rs.getString("password"));
				}
				System.out.println(rs.getString("email"));*/
				response.sendRedirect(contextpath+"/LoginSuccess.jsp");
			}
				
			
			
			
			
		}catch (Exception e) {
	          System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	          System.exit(0);
	       }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}