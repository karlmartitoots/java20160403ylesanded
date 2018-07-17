package main.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.bureauActivation.RandomHashFactory;
import main.bureauActivation.SendEmail;
import main.search.DBConnection;

/**
 * Servlet implementation class LostPassServlet
 */
public class LostPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostPassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		
		DBConnection connection = new DBConnection();

		Connection curconnection = null;

		curconnection = connection.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Statement smt = null;
		ResultSet rst = null;

		String contextpath = request.getContextPath();
		
		try {
			stmt = curconnection.prepareStatement("SELECT hash FROM bureau WHERE email='" + email + "';");			
			rs = stmt.executeQuery();
			smt = curconnection.createStatement();			
			if (!rs.next()){
				response.sendRedirect(contextpath + "/NoEmailFound.jsp");
				curconnection.close();
			}			
			
			else {
				RandomHashFactory newPassHash = new RandomHashFactory(8);
				String newPass = newPassHash.nextString();				
				smt.executeUpdate("UPDATE bureau SET password='" + newPass + "' WHERE email='" + email + "';");
				SendEmail sendNewPass = new SendEmail(email, newPass, "lostPass");
				sendNewPass.sendMail();
				response.sendRedirect(contextpath + "/NewPassSent.jsp");
			}
		}
		catch (SQLException e){
			System.out.println("LostPassServlet error");
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
