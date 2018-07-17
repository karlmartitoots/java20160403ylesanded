package main.admin;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.search.DBConnection;
import main.details.BureauDetails;

/**
 * Servlet implementation class ActivateInActivateServlet
 */
public class ActivateInActivateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivateInActivateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int category = 2;
		int active = Integer.parseInt(request.getParameter("param"));
		int activeLogic = (active == 1) ? 0:1;
		String bureauId = request.getParameter("paramTwo");
        DBConnection connect = new DBConnection();

        PreparedStatement preparedSQLUpdate = connect.prepareStatement();
        String sqlUpdate = "UPDATE bureau SET active=" + activeLogic + " WHERE bureauid=" + bureauId + ";";
        String sqlSelect = "SELECT  bureauid, name, email, active from bureau where category='" + category + "';";

        ArrayList<BureauDetails> bureau = new ArrayList<BureauDetails>();





		ResultSet resultSet = null;


        try (
                Connection currentConnection = connect.getConnection()) {
            Statement statement = currentConnection.createStatement();
            statement.executeUpdate(sqlUpdate);
            resultSet = statement.executeQuery(sqlSelect);

            while (resultSet.next()) {

				BureauDetails br = new BureauDetails();
				
				br.setBureauId(resultSet.getInt("bureauid"));
				br.setBureauName(resultSet.getString("name"));
				br.setEmail(resultSet.getString("email"));
				br.setActive(resultSet.getInt("active"));

				bureau.add(br);

			}

			request.setAttribute("bureau", bureau);
			request.getRequestDispatcher("Admin.jsp").forward(request, response);

			resultSet.close();

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
