package main.bureauEdit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import main.details.FieldDetails;
import main.search.DBConnection;

public class FieldsDBEdit {
	
	private ArrayList<FieldDetails> fields;
	int bureauId;
	
	public FieldsDBEdit(ArrayList<FieldDetails> fields, int bureauId){
		this.fields = fields;
		this.bureauId = bureauId;
	}
	
	public void addToDatabase() {
		DBConnection connect = new DBConnection();		
		PreparedStatement smt = null;		
		Connection curConnection = null;
		curConnection = connect.getConnection();
		try{
			for (int i=0; i<fields.size(); i++){
				FieldDetails field = fields.get(i);
				if (field.isAdd()){
					String sql = "INSERT INTO fieldbureaujunction VALUES (" + bureauId  +"," + field.getFieldId() + ");";
					smt = curConnection.prepareStatement(sql);
					smt.executeUpdate();
				}
				if (field.isDelete()){
					String sql = "DELETE FROM fieldbureaujunction WHERE bureauid = " + bureauId + " AND fieldid = " + field.getFieldId() + ";";
					smt = curConnection.prepareStatement(sql);
					smt.executeUpdate();
				}
			}
			
			smt.close();
			curConnection.close();
		}
		catch (SQLException e){
			e.printStackTrace();			
		}
	}

}
