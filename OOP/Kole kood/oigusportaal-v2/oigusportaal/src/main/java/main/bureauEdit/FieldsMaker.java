package main.bureauEdit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.search.DBConnection;

public class FieldsMaker {

	int generalId;
	boolean isStory;

	public FieldsMaker(int generalId, boolean isStory) {
		this.generalId = generalId;
		this.isStory = isStory;
	}

	public ArrayList<Integer> getCheckedBoxes() {
		ArrayList<Integer> checkBoxes = new ArrayList<Integer>();

		DBConnection connect = new DBConnection();
		Statement smt = null;
		ResultSet rst = null;
		Connection curConnection = connect.getConnection();

		try {
			smt = curConnection.createStatement();
			if (!isStory) {
				// System.out.println("BürooId on " + bureauId);

				rst = smt
						.executeQuery("SELECT fieldid FROM fieldbureaujunction WHERE bureauid='"
								+ generalId + "';");

				while (rst.next()) {
					checkBoxes.add(rst.getInt("fieldid"));
				}
			} else {
				if (generalId!= 0){
				rst = smt
						.executeQuery("SELECT fieldid FROM successstory WHERE ssid='"
								+ generalId + "';");
				while (rst.next()) {
					checkBoxes.add(rst.getInt("fieldid"));
				}
			}
			smt.close();
			curConnection.close();
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return checkBoxes;
	}

	public ArrayList<String> getCheckBoxCode() {
		ArrayList<String> codes = new ArrayList<String>();
		ArrayList<Integer> checkedBoxes = getCheckedBoxes();
		ArrayList<String> names = new ArrayList<String>();

		DBConnection connect = new DBConnection();
		Statement smt = null;
		ResultSet rst = null;
		Connection curConnection = connect.getConnection();

		try {
			smt = curConnection.createStatement();
			rst = smt.executeQuery("SELECT * FROM field;");

			while (rst.next()) {
				names.add(rst.getString("fieldname"));
			}

			smt.close();
			curConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		System.out.println("Codes size: " + codes.size());
//		System.out.println("Names size: " + names.size());
		if (!isStory) {
			for (int i = 1; i < names.size()+1; i++) {
				String row = "<input type=\"checkbox\" id=\"cbox" + i
						+ "\" name=\"cbox" + i + "\" value=\"" + i
						+ "\"";
				if (checkedBoxes.contains(new Integer(i))) {
					row = row.concat(" checked ");
				}
				row = row.concat(">" + names.get(i-1) + "<br>");
				codes.add(row);
				// Debugging
//				System.out.println(row);

			}

			String oldCBoxes = "<input type=\"hidden\" id=\"oldCBoxes\" name=\"oldCBoxes\" value=\"";
			for (int i = 0; i < checkedBoxes.size(); i++) {
				if (i != checkedBoxes.size() - 1)
					oldCBoxes = oldCBoxes.concat(checkedBoxes.get(i).toString()
							+ ";");
				else {
					oldCBoxes = oldCBoxes
							.concat(checkedBoxes.get(i).toString());
				}
			}

			oldCBoxes = oldCBoxes.concat("\">");
//			System.out.println(oldCBoxes);
			codes.add(oldCBoxes);
		}
		
		else {
			for (int i = 1; i < names.size()+1; i++){
				String row = "<input type=\"radio\" name=\"fields\" value=\"" + i + "\" ";
				if (checkedBoxes.contains(new Integer(i))) {
					row = row.concat("checked ");
				}
				row = row.concat(">" + names.get(i-1) + "<br>");
				codes.add(row); 
			}
		}
		return codes;
	}

}
