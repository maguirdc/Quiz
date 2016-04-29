package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//input: fist_name
public class SearchSQL {

	public SearchSQL() {

	}

	public ArrayList<Pupil> searchSQL(String input) {
		System.out.println("sql input: " + input);
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

			if (input.isEmpty()) {
				System.out.println("input is null");
				sql_string = "SELECT pupil_id, first_name, " + "last_name, class_id, enrolled " + "FROM pupils ";

			} else {
				System.out.println("input is not null");
				sql_string = "SELECT pupil_id, first_name, last_name, class_id, enrolled " + "FROM pupils "
						+ "WHERE first_name = " + "'" + input + "'";
			}

			System.out.println("SearchSQL running:" + sql_string);

			ResultSet rs = stmt.executeQuery(sql_string);

			while (rs.next()) {

				Pupil pupil = new Pupil();

				/*
				 * System.out.println("Returned:" + rs.getString("pupil_id"));
				 * System.out.println("Returned:" + rs.getString("first_name"));
				 * System.out.println("Returned:" + rs.getString("last_name"));
				 * System.out.println("Returned:" + rs.getString("class_id"));
				 */
				// System.out.println("Returned:" +
				// rs.getString("guardian_id"));
				// System.out.println("Returned:" + rs.getString("email"));

				pupil.setId(rs.getString("pupil_id"));
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

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		// END OF JDBC RELATED
		// CONTENT----------------------------------------------------------------
		// -------------------------------------------------------------------------------------------
		return pupil_List;

	}

	// method 2)
	public ArrayList<LoginCred> searchLogin(String userName, String passWord) {

		System.out.println("Inputting: " + userName + " " + passWord);

		ArrayList<LoginCred> login_List = new ArrayList<>();

		try {
			Connection con;
			Statement stmt;

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema01", "root", "laggingsql34");
			stmt = con.createStatement();

			String sql_string;

			sql_string = "SELECT * FROM login_entities";

			System.out.println("SearchSQL running:" + sql_string);

			ResultSet rs = stmt.executeQuery(sql_string);

			while (rs.next()) {

				LoginCred loginCred = new LoginCred();

				loginCred.setUsername(rs.getString("Username"));
				loginCred.setPassword(rs.getString("Password"));

				login_List.add(loginCred);

				System.out.println("loginCred username contains" + loginCred.getUsername().toString());
				System.out.println("loginCred password contains" + loginCred.getPassword().toString());

			}

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		// END OF JDBC RELATED
		// CONTENT----------------------------------------------------------------
		// -------------------------------------------------------------------------------------------
		return login_List;

	}

	// method 3)
	public ArrayList<staffLoginCred> searchStaffLogin() {

		ArrayList<staffLoginCred> staffLogin_List = new ArrayList<>();

		try {
			Connection con;
			Statement stmt;

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema01", "root", "laggingsql34");
			stmt = con.createStatement();

			String sql_string;

			sql_string = "SELECT * FROM login_staff;";

			System.out.println("SearchSQL running:" + sql_string);

			ResultSet rs = stmt.executeQuery(sql_string);

			while (rs.next()) {

				staffLoginCred staffLoginCred = new staffLoginCred();

				staffLoginCred.setUsername(rs.getString("Username"));
				staffLoginCred.setUsername(rs.getString("accessLevel"));
				staffLoginCred.setPassword(rs.getString("Password"));

				staffLogin_List.add(staffLoginCred);

				System.out.println("loginCred username contains" + staffLoginCred.getUsername().toString());
				System.out.println("loginCred access level contains" + staffLoginCred.getAccessLevel().toString());
				System.out.println("loginCred password contains" + staffLoginCred.getPassword().toString());

			}

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		// END OF JDBC RELATED
		// CONTENT----------------------------------------------------------------
		// -------------------------------------------------------------------------------------------
		return staffLogin_List;

	}

	// method 4)
	public ArrayList<AdminLoginCred> searchAdminLogin() {

		ArrayList<AdminLoginCred> AdminLogin_List = new ArrayList<>();

		try {
			Connection con;
			Statement stmt;

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema01", "root", "laggingsql34");
			stmt = con.createStatement();

			String sql_string;

			sql_string = "SELECT * FROM login_admins;";

			System.out.println("SearchSQL running:" + sql_string);

			ResultSet rs = stmt.executeQuery(sql_string);

			while (rs.next()) {

				AdminLoginCred staffLoginCred = new AdminLoginCred();

				staffLoginCred.setUsername(rs.getString("Username"));
				staffLoginCred.setAccessLevel(rs.getString("Access_level"));
				staffLoginCred.setPassword(rs.getString("Password"));

				AdminLogin_List.add(staffLoginCred);

				System.out.println("staffLoginCred username contains " + staffLoginCred.getUsername().toString());
				System.out
						.println("staffLoginCred  access level contains " + staffLoginCred.getAccessLevel().toString());
				System.out.println("staffLoginCred  password contains " + staffLoginCred.getPassword().toString());

			}

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		// END OF JDBC RELATED
		// CONTENT----------------------------------------------------------------
		// -------------------------------------------------------------------------------------------
		return AdminLogin_List;

	}

	// method 5)
	public ArrayList<TeacherLoginCred> searchTeachLogin() {

		ArrayList<TeacherLoginCred> TeachLogin_List = new ArrayList<>();

		//---------------------------------------------------------------------------------------------------
		try {
			Connection con;
			Statement stmt;

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema01", "root", "laggingsql34");
			stmt = con.createStatement();

			String sql_string;

			sql_string = "SELECT * FROM login_teachers;";

			System.out.println("SearchSQL running:" + sql_string);

			ResultSet rs = stmt.executeQuery(sql_string);

			while (rs.next()) {

				TeacherLoginCred staffLoginCred = new TeacherLoginCred();

				staffLoginCred.setUsername(rs.getString("Username"));
				staffLoginCred.setAccessLevel(rs.getString("Access_level"));
				staffLoginCred.setPassword(rs.getString("Password"));

				TeachLogin_List.add(staffLoginCred);

				System.out.println("staffLoginCred username contains " + staffLoginCred.getUsername().toString());
				System.out.println("staffLoginCred  access level contains " + staffLoginCred.getAccessLevel().toString());
				System.out.println("staffLoginCred  password contains " + staffLoginCred.getPassword().toString());

			}

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		// END OF JDBC RELATED
		// CONTENT----------------------------------------------------------------
		// -------------------------------------------------------------------------------------------
		return TeachLogin_List;

	}

	// method 6)
	public ArrayList<PupilLoginCred> searchPupilLogin() {

		ArrayList<PupilLoginCred> PupilLogin_List = new ArrayList<>();

		try {
			Connection con;
			Statement stmt;

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema01", "root", "laggingsql34");
			stmt = con.createStatement();

			String sql_string;

			sql_string = "SELECT * FROM login_pupils;";

			System.out.println("SearchSQL running:" + sql_string);

			ResultSet rs = stmt.executeQuery(sql_string);

			while (rs.next()) {

				PupilLoginCred staffLoginCred = new PupilLoginCred();

				staffLoginCred.setUsername(rs.getString("Username"));
				staffLoginCred.setPassword(rs.getString("Password"));

				PupilLogin_List.add(staffLoginCred);

				System.out.println("staffLoginCred username contains " + staffLoginCred.getUsername().toString());
				System.out.println("staffLoginCred  password contains " + staffLoginCred.getPassword().toString());

			}

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		// END OF JDBC RELATED
		// CONTENT----------------------------------------------------------------
		// -------------------------------------------------------------------------------------------
		return PupilLogin_List;

	}
}
