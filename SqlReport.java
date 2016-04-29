package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlReport {
	
	
	public SqlReport() {

	}

	//method 1
	public ArrayList<Score> searchReport(String input) {
		System.out.println("sql input: " + input);
		ArrayList<Score> score_List = new ArrayList<>();

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

			
			
			sql_string = "SELECT score.pupil_id, score.quiz_id, score.score, score.date_taken, "
					+ "quiz_by_category.category " 
					+ "FROM score " 
					+ "JOIN quiz_by_category ON (score.quiz_id = quiz_by_category.quiz_id)"
					+ "WHERE score.pupil_id = '" + input + "';";

			System.out.println("SearchSQL running:" + sql_string);

			ResultSet rs = stmt.executeQuery(sql_string);

			while (rs.next()) {

				Score score = new Score();

				
				 System.out.println("Returned:" + rs.getString("quiz_id"));
				 System.out.println("Returned:" + rs.getString("score"));
				 System.out.println("Returned:" + rs.getString("category"));
				 System.out.println("Returned:" + rs.getString("date_taken"));
				 
				// System.out.println("Returned:" +
				// rs.getString("guardian_id"));
				// System.out.println("Returned:" + rs.getString("email"));

				score.setQuizId(rs.getString("quiz_id"));
				score.setScore(rs.getInt("score"));
				
				String category = rs.getString("category");
				score.setCategory(convertQuizCategoryToQuizType(category));
				
				score.setDate(rs.getString("date_taken"));
				//int val = rs.getInt("enrolled");
				score_List.add(score);

			}

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		// END OF JDBC RELATED
		// CONTENT----------------------------------------------------------------
		// -------------------------------------------------------------------------------------------
		return score_List;

	}
	
	//method 2
	private QuizType convertQuizCategoryToQuizType(String type)
	{
		if (type.toLowerCase().equals("chemistry")) return QuizType.CHEMISTRY;
		if (type.toLowerCase().equals("biology")) return QuizType.BIOLOGY;
		//if (type.toLowerCase().equals("physics")) 
			return QuizType.PHYSICS;
	}	

	
	//Method 3
	public Profile searchProfile(String input) {
		System.out.println("sql profile input: " + input);
		Profile profile = new Profile();

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

			sql_string = "SELECT pupils.first_name AS pupil_first, pupils.last_name AS pupil_last, "
					+ "pupils.email AS pupil_email, pupils.guardian_id , "
					+ "guardians.guardian_id, guardians.first_name AS guardian_first, "
					+ "guardians.last_name AS guardian_last, guardians.email AS guardian_email, "
					+ "guardians.mobile_number "
					+ "FROM pupils "
					+ "JOIN guardians on (pupils.guardian_id = guardians.guardian_id) "
					+ "WHERE pupil_id = '" + input +"';";

			System.out.println("SearchSQL running:" + sql_string);

			ResultSet rs = stmt.executeQuery(sql_string);

			while (rs.next()) {
				
				profile.setId("");
				profile.setPupilFirstName(rs.getString("pupil_first"));
				profile.setPupilLastName(rs.getString("pupil_last"));
				profile.setPupilEmail(rs.getString("pupil_email"));
				profile.setFirstName(rs.getString("guardian_first"));
				profile.setLastName(rs.getString("guardian_last"));
				profile.setEmail(rs.getString("guardian_email"));
				//String mob = Integer.toString(rs.getString("mobile_number"));
				profile.setMobileNumber(rs.getString("mobile_number"));

				System.out.println("DB RETURN "+rs.getString("pupil_first"));
			}

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		// END OF JDBC RELATED
		// CONTENT----------------------------------------------------------------
		// -------------------------------------------------------------------------------------------
		return profile;

	}
}
