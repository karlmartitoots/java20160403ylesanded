package main.bureauEdit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.details.FieldDetails;
import main.login.UserBean;
import main.search.DBConnection;

public class BureauEditServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BureauEditServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		UserBean user = new UserBean();

		HttpSession session = request.getSession(true);
		session.setAttribute("currentSessionUser", user);
		session.setMaxInactiveInterval(3000);

		DBConnection connect = new DBConnection();

		Connection curConnection = null;

		PreparedStatement smt = null;

		curConnection = connect.getConnection();

		String email = request.getParameter("oldEmail");
//		System.out.println(email);
		String password = request.getParameter("oldPassword");
//		System.out.println(password);
		int phone = Integer.parseInt(request.getParameter("oldPhone"));
		int price = Integer.parseInt(request.getParameter("oldPrice"));
		String address = request.getParameter("oldAddress");
		int regCode = Integer.parseInt(request.getParameter("oldRegistryCode"));
		int zipCode = Integer.parseInt(request.getParameter("oldPostalCode"));
		int region = Integer.parseInt(request.getParameter("oldRegion"));
		int county = Integer.parseInt(request.getParameter("oldCounty"));
		int city = Integer.parseInt(request.getParameter("oldCity"));

		user.setEmail(email);
		user.setPassword(password);
		// user.setPhone(phone);
		user.setStreet(address);
		user.setRegistryCode(regCode);

		boolean phoneChanged = false;
		boolean addressChanged = false;
		boolean priceChanged = false;		
		boolean emailChanged = false;
		boolean passChanged = false;
		boolean zipCodeChanged = false;
		boolean regionChanged = false;
		boolean countyChanged = false;
		boolean cityChanged = false;
		int bureauId = 0;

		String oldCBoxes = request.getParameter("oldCBoxes");
		ArrayList<String> oldCBoxesList = new ArrayList<String>();
		if (oldCBoxes.length()>0){
		oldCBoxesList = new ArrayList<String>(
				Arrays.asList(oldCBoxes.split(";")));
		}
		ArrayList<Integer> oldCBoxesListInt = new ArrayList<Integer>();
		for (int i = 0; i < oldCBoxesList.size(); i++) {
			int oldCBox = Integer.parseInt(oldCBoxesList.get(i));
			oldCBoxesListInt.add(oldCBox);
			//Debug
//			System.out.println("Old Checked box: " + oldCBox);
		}

		ArrayList<Integer> newCBoxesList = new ArrayList<Integer>();

		// HARDCODED; change it when need to add/delete fields
		final int LIST_LENGTH = 26;

		for (int i = 1; i < LIST_LENGTH + 1; i++) {
			String argument = "cbox" + i;
			//Debug			
			if (request.getParameter(argument) != null) {
				Integer newCBox = new Integer(i);
				newCBoxesList.add(newCBox);
				//Debug
//				System.out.println("Checked box: " + newCBox.intValue());
			}
		}

		ArrayList<FieldDetails> fields = new ArrayList<FieldDetails>();

		
		for (int i = 0; i< oldCBoxesListInt.size(); i++) {
			int occurrences = 0;
			for (int j = 0; j < newCBoxesList.size(); j++){
				if (oldCBoxesListInt.get(i).equals(newCBoxesList.get(j))){
					occurrences++;
				}
			}
			if (occurrences == 0){
				FieldDetails newField = new FieldDetails(oldCBoxesListInt.get(i).intValue());
				newField.setDelete(true);
				fields.add(newField);
				//Debug
//				System.out.println("To deletion: " + newField.getFieldId());
			}
		}
		
		for (int i = 0; i< newCBoxesList.size(); i++){
			int occurrences = 0;
			for (int j = 0; j < oldCBoxesListInt.size(); j++){
				if (newCBoxesList.get(i).equals(oldCBoxesListInt.get(j))){
					occurrences++;
				}
			}
			if (occurrences == 0){
				FieldDetails newField = new FieldDetails(newCBoxesList.get(i).intValue());
				newField.setAdd(true);
				fields.add(newField);
				//Debug
//				System.out.println("To add: " + newField.getFieldId());
			}
		}


		

		Statement statementUser = null;
		ResultSet resultSetUser = null;

		try {

			int newPhone = Integer.parseInt(request
					.getParameter("profileBureauPhone"));
			if (newPhone != phone) {
				phoneChanged = true;
//				System.out.println("Phone: " + phone + " " + newPhone);

			}

			String newAddress = request
					.getParameter("profileBureauAddress");
			if (!newAddress.equals(address)) {
				addressChanged = true;
//				System.out.println("Address: " + address + " " + newAddress);
			}

			int newPrice = Integer.parseInt(request
					.getParameter("profileBureauAveragePrize"));
			if (newPrice != price || newPrice < 0) {
				priceChanged = true;
//				System.out.println("Price: " + price + " " + newPrice);

			}
			
			int newZipCode = Integer.parseInt(request.getParameter("profilePostalCode"));			
			if (newZipCode != zipCode){
				zipCodeChanged = true;
			}



			String newEmail = request
					.getParameter("profileBureauEmail");
			if (!newEmail.equals(email)) {
				emailChanged = true;
//				System.out.println("Email: " + email + " " + newEmail);
			}

			String newPassword = request
					.getParameter("newPass");
			String newPasswordConfirm = request.getParameter("newPassConfirm");
			if (newPassword.equals(newPasswordConfirm)){
				if (!newPassword.equals(password) && !newPassword.equals("")
						&& newPassword != null) {
					passChanged = true;	
				}
			}
			
			int newRegion = Integer.parseInt(request.getParameter("regions"));
			
			if (region != newRegion){
				regionChanged = true;
			}
			
			int newCounty = Integer.parseInt(request.getParameter("counties"));
			
			if (county != newCounty){
				countyChanged = true;
			}
			
			int newCity = Integer.parseInt(request.getParameter("cities"));
			
			if (city != newCity){
				cityChanged = true;
			}
			
			
			NameToValueDecrypter areaNames = new NameToValueDecrypter(newCity, newRegion, newCounty);
			

			String statement = "";

			if (!phoneChanged && !addressChanged && !priceChanged
					&& !emailChanged && !passChanged && !zipCodeChanged && !regionChanged && !countyChanged && !cityChanged) {
//				System.out.println("Made it to nothing changed");
			} else {
				statement = "UPDATE bureau SET ";
				if (phoneChanged) {
//					System.out.println("Made it to phonechange");
					user.setPhone(newPhone);
					statement = statement.concat("phone='" + newPhone + "', ");
				}
				if (addressChanged) {
//					System.out.println("Made it to addresschange");
					user.setStreet(newAddress);
					statement = statement.concat("street='" + newAddress
							+ "', ");
				}
				if (priceChanged) {
//					System.out.println("Made it to pricechange");
					user.setAveragePrice(newPrice);
					statement = statement.concat("averageprice='" + newPrice
							+ "', ");
				}				
				if (emailChanged) {
//					System.out.println("Made it to emailchange");
					user.setEmail(newEmail);
					statement = statement.concat("email='" + newEmail + "', ");
				}
				if (passChanged) {
//					System.out.println("Made it to passwordchange");
					user.setPassword(newPassword);
					statement = statement.concat("password='" + newPassword
							+ "', ");
					request.setAttribute("passChanged", true);
				}
				if (zipCodeChanged) {
					user.setPostalcode(newZipCode);
					statement = statement.concat("postalcode='" + newZipCode + "', ");
				}
				if (regionChanged) {
					String regionName = areaNames.getRegionName();
					user.setRegionName(regionName);
					statement = statement.concat("regionname='" + regionName + "', ");
				}
				if (countyChanged) {
					String countyName = areaNames.getCountyName();
					user.setCountyName(countyName);
					statement = statement.concat("countyname='" + countyName + "', ");
				}
				if (cityChanged) {
					String cityName = areaNames.getCityName();
					user.setCityName(cityName);
					statement = statement.concat("cityname='" + cityName + "', ");
				}
				
			}

			if (statement.endsWith(", ")) {
				statement = statement.substring(0, statement.length() - 2);
			}

			if (!statement.equals("")) {
				statement = statement.concat(" WHERE email='" + email
						+ "' AND password='" + password + "';");
//				System.out.println(statement);

				smt = curConnection.prepareStatement(statement);
				smt.executeUpdate();
				smt.close();

			}

			statementUser = curConnection.createStatement();
			resultSetUser = statementUser
					.executeQuery("SELECT bureauid FROM bureau WHERE email='"
							+ email + "' AND password='" + password + "';");

			while (resultSetUser.next()) {
				bureauId = (Integer) resultSetUser.getInt("bureauid");

			}

			resultSetUser.close();
			statementUser.close();

			curConnection.close();
			
			if (fields.size() != 0 ){
				FieldsDBEdit fieldsToDB = new FieldsDBEdit(fields, bureauId);
				fieldsToDB.addToDatabase();
			}
			request.setAttribute("user", user);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/BureauProfileServlet");
			rd.forward(request, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
