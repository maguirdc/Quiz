package quiz;

import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EditSQL {

	public EditSQL() {

	}

	public void editSQL(String input01, String input02, String input03, int input04) {

		ArrayList<Pupil> pupil_List = new ArrayList<>();

		try {
			Connection con;
			Statement stmt;

			// USER YOU WILL NEED TO CHANGE getConnection() FOR YOUR SQL
			// WORKBENCH
			// con = DriverManager.getConnection(your url, "your username",
			// "your password");

			// String search_id = pupilTxtfSearch.getText();

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema01", "root", "laggingsql34");
			stmt = con.createStatement();

			String sql_string;

			System.out.println("inside EditSQL");
			
			//UPDATE pupils SET enrolled = 0 WHERE pupil_id = 'PU000001'
			/*input01 = "pupils";
			input02 = "pupil_id";
			input03 = "PU000002";
			*/
			if(input04 == 1){
				
				sql_string = "UPDATE " + input01 + " SET enrolled = 1 " + " WHERE " + input02 + " = '" + input03 + "'";
				
			}else{
				
				sql_string = "UPDATE " + input01 + " SET enrolled = 0 " + " WHERE " + input02 + " = '" + input03 + "'";
			}
				
			
			
			/*
			if (input == null) {
				System.out.println("input is null");
				//UPDATE pupils
				//SET enrolled = 0
				//	 WHERE pupil_id = 'PU000001'
				String input01 = "pupils";
				String input02 = "pupil_id";
				String input03 = "PU000002";
				
				sql_string = "UPDATE" + input01 + "SET enrolled = 0" + "WHERE " + input02 + " = '" + input03 + "'";
			
			} else {
				System.out.println("input is not null");
				sql_string = "SELECT pupil_id, first_name, last_name, class_id, enrolled " + "FROM pupils "
						+ "WHERE first_name = " + "'" + input + "'";
			}
			*/
			
			System.out.println("EditSQL running: " + sql_string);
			
			stmt.executeUpdate(sql_string);
			
			/*
			ResultSet rs = stmt.executeQuery(sql_string);
			
			while (rs.next()) {

				Pupil pupil = new Pupil();

				
				  System.out.println("Returned:" + rs.getString("pupil_id"));
				  System.out.println("Returned:" + rs.getString("first_name"));
				  System.out.println("Returned:" + rs.getString("last_name"));
				  System.out.println("Returned:" + rs.getString("class_id"));
				 
				// System.out.println("Returned:" +
				// rs.getString("guardian_id"));
				// System.out.println("Returned:" + rs.getString("email"));

				pupil.setPupilId(rs.getString("pupil_id"));
				pupil.setFirstName(rs.getString("first_name"));
				pupil.setLastName(rs.getString("last_name"));
				pupil.setClassId(rs.getString("class_id"));
				int val = rs.getInt("enrolled");

				if (val == 0) {
					pupil.setEnrolled(false);

				} else {// 1 is true
					pupil.setEnrolled(true);
				}

				// System.out.println("put into list: " + pupil_List);
				pupil_List.add(pupil);

			}
			*/

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		// END OF JDBC RELATED
		// CONTENT----------------------------------------------------------------
		// -------------------------------------------------------------------------------------------

	}
}
