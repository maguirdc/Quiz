package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//input: fist_name
public class PeopleRepository {

	private Connection con = null;
	
	public PeopleRepository() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSL=false", "root", "Pa$$w0rd");
	}

	public ArrayList<Pupil> searchSQL(String input) {

		ArrayList<Pupil> pupil_List = new ArrayList<>();

		try {
			Statement stmt;

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

	public ArrayList<LoginCred> searchLogin(String userName, String passWord) {

		System.out.println("Inputting: " + userName + " " + passWord);

		ArrayList<LoginCred> login_List = new ArrayList<>();

		try {
			Statement stmt;

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

	public ArrayList<staffLoginCred> searchStaffLogin() {

		ArrayList<staffLoginCred> staffLogin_List = new ArrayList<>();

		try {
			Statement stmt;

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

	public ArrayList<AdminLoginCred> searchAdminLogin() {

		ArrayList<AdminLoginCred> AdminLogin_List = new ArrayList<>();

		try {
			Statement stmt;

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

	public ArrayList<TeacherLoginCred> searchTeachLogin() {

		ArrayList<TeacherLoginCred> TeachLogin_List = new ArrayList<>();

		try {
			Statement stmt;

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

	public ArrayList<PupilLoginCred> searchPupilLogin() {

		ArrayList<PupilLoginCred> PupilLogin_List = new ArrayList<>();

		try {
			Statement stmt;

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

	public Staff authenicateStaff(Staff staff) throws SQLException
	{
		Statement stmt = con.createStatement();

		String query = String.format("SELECT Username, Access_Level, Password "
				+ "FROM login_staff "
				+ "WHERE username = '%s' AND password = '%s'", staff.getEmail(), staff.getUser_password());

		ResultSet rs = stmt.executeQuery(query);

		Staff retrievedStaff = new Staff();
		
		while (rs.next()) {
			retrievedStaff.setEmail(rs.getString("Username"));
			retrievedStaff.setAccess_level(rs.getString("Access_Level"));
		}
		
		return retrievedStaff;

	}
	
	public Pupil authenicatePupil(Pupil pupil) throws SQLException
	{
		Statement 		stmt = con.createStatement();

		String query = String.format("SELECT Username, Access_Level, Password "
				+ "FROM login_staff "
				+ "WHERE username = '%s' AND password = '%s'", pupil.getEmail(), pupil.getPassword());

		ResultSet rs = stmt.executeQuery(query);

		Pupil retrievedPupil = new Pupil();
		
		while (rs.next()) {
			retrievedPupil.setEmail(rs.getString("Username"));
		}
		
		return retrievedPupil;
	}
	
	public ArrayList<Pupil> getAllPupils() throws SQLException
	{
		Statement stmt = con.createStatement();

		String query = "SELECT pupil_id, first_name, last_name, class_id, guardian_id, email, enrolled "
				+ "FROM pupils";

		ResultSet rs = stmt.executeQuery(query);

		ArrayList<Pupil> pupils = new ArrayList<Pupil>();
		
		while (rs.next()) {
			Pupil retrievedPupil = new Pupil();
			retrievedPupil.setId(rs.getString("pupil_id"));
			retrievedPupil.setFirstName(rs.getString("first_name"));
			retrievedPupil.setLastName(rs.getString("last_name"));
			retrievedPupil.setClassId(rs.getString("class_id"));
			retrievedPupil.setGuardianId(rs.getString("guardian_id"));
			retrievedPupil.setEmail(rs.getString("email"));
			retrievedPupil.setEnrolled(rs.getBoolean("enrolled"));
			
			pupils.add(retrievedPupil);
		}
		
		return pupils;
	}
	
	public Pupil getPupilById(String id) throws SQLException {
		Statement stmt = con.createStatement();

		String query = String.format("SELECT pupil_id, first_name, last_name, class_id, guardian_id, email, enrolled "
				+ "FROM pupils WHERE pupil_id = '%s'", id);

		ResultSet rs = stmt.executeQuery(query);

		Pupil retrievedPupil = new Pupil();
		
		while (rs.next()) {
			retrievedPupil.setId(rs.getString("pupil_id"));
			retrievedPupil.setFirstName(rs.getString("first_name"));
			retrievedPupil.setLastName(rs.getString("last_name"));
			retrievedPupil.setClassId(rs.getString("class_id"));
			retrievedPupil.setGuardianId(rs.getString("guardian_id"));
			retrievedPupil.setEmail(rs.getString("email"));
			retrievedPupil.setEnrolled(rs.getBoolean("enrolled"));
		}
		
		return retrievedPupil;
	}
	
	public void enrollStudentById(String id) throws SQLException
	{
		Statement stmt = con.createStatement();

		String query = String.format("UPDATE pupils "
				+ "SET enrolled = true "
				+ "WHERE pupil_id = '%s'", id);

		int rowsUpdated = stmt.executeUpdate(query);
	}
	
	public void unenrollStudentById(String id) throws SQLException
	{
		Statement stmt = con.createStatement();

		String query = String.format("UPDATE pupils "
				+ "SET enrolled = false "
				+ "WHERE pupil_id = '%s'", id);

		int rowsUpdated = stmt.executeUpdate(query);
	}
}
