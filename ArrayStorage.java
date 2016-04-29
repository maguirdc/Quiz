package quiz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArrayStorage {

	ArrayList<String> adminUsernames = new ArrayList<String>();
	
	
	ArrayList<String> adminPasswords = new ArrayList<String>();
	
	
	
	
	ArrayList<String> studentUsernames = new ArrayList<String>();
	
	ArrayList<String> studentPasswords = new ArrayList<String>();
	
	
	
	
	
	ArrayList<String> teacherStaffUsernames = new ArrayList<String>();
	
	ArrayList<String> teacherStaffPasswords = new ArrayList<String>();
	
	ArrayList<String> teacherStaffIDs = new ArrayList<String>();
	
	ArrayList<String> teacherStaffFirstName = new ArrayList<String>();
	
	ArrayList<String> teacherStaffLastName = new ArrayList<String>();
	
	ArrayList<String> teacherStaffPosition = new ArrayList<String>();
	
	ArrayList<String> teacherStaffAccessLevel = new ArrayList<String>();
	
	ArrayList<String> teacherStaffEmailAddress = new ArrayList<String>();
	
	
	
	
	
	
	
	
	ArrayList<String> biologyQuestions = new ArrayList<String>();
	ArrayList<String> chemistryQuestions = new ArrayList<String>();
	ArrayList<String> physicsQuestions = new ArrayList<String>();
	
	
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub

	}

	public void addStudentsToDatabase(String pupilID, String pupilFirstName, String pupilLastName, 
			String pupilClassID, String pupilGuardianID, String pupilEmailAddress,String pupilPassword,
			String pupilEnrollment){
		
		///put JDBC connection in here
		
		String subString01 = pupilID;
		String subString02 = pupilFirstName;
		String subString03 = pupilLastName;
		String subString04 = pupilClassID;
		String subString05 = pupilGuardianID;
		String subString06 = pupilEmailAddress;
		String subString07 = pupilPassword;
		String subString08 = pupilEnrollment;
		
		String string01 = "insert into Pupils values(" + 
				"'" + subString01 + "'," +
				"'" + subString02 + "'," +
				"'" + subString03 + "'," +
				"'" + subString04 + "'," +
				"'" + subString05 + "'," +
				"'" + subString06 + "'," +
				"'" + subString07 + "'," +
				"'" + subString08 + "');";
		
		//-------------------------------------------------------------------------------------------------
		//JDBC RELATED CONTENT------------------------------------------------------------------------------
		//Create a new JDBC connection
		Connection con;
		Statement stmt;

		try {
			
			//USER YOU WILL NEED TO CHANGE getConnection() FOR YOUR SQL WORKBENCH
			//con = DriverManager.getConnection(your url, "your username", "your password");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema01","root" ,"laggingsql34");        	
			stmt = con.createStatement();
			
	        //1)ADD DATA TO THE TABLE WITH A NEW ROW
	        System.out.println(string01);
			stmt.executeUpdate(string01);
	        
			
			System.out.println("The program has executed");
			stmt.close();
			con.close();
			
			} catch(SQLException ex){
				System.err.println("SQLException: " + ex.getMessage());
			}
		
		//END OF JDBC RELATED CONTENT----------------------------------------------------------------
		//-------------------------------------------------------------------------------------------
			
		
	}
	
	
	public void addStaffToDatabase(String staffID, String staffFirstName, String staffLastName, String staffPosition, String staffAccessLevel, String staffPassword, String staffEmailAddress){
		
		
		///put JDBC connection in here
		
		///put JDBC connection in here
		
				String subString01 = staffID;
				String subString02 = staffFirstName;
				String subString03 = staffLastName;
				String subString04 = staffPosition;
				String subString05 = staffAccessLevel;
				String subString06 = staffEmailAddress;
				String subString07 = staffPassword;
				
				String string01 = "insert into Staff values(" + 
						"'" + subString01 + "'," +
						"'" + subString02 + "'," +
						"'" + subString03 + "'," +
						"'" + subString04 + "'," +
						"'" + subString05 + "'," +
						"'" + subString06 + "'," +
						"'" + subString07 + "');";
				
				//-------------------------------------------------------------------------------------------------
				//JDBC RELATED CONTENT------------------------------------------------------------------------------
				//Create a new JDBC connection
				Connection con;
				Statement stmt;

				try {
					
					//USER YOU WILL NEED TO CHANGE getConnection() FOR YOUR SQL WORKBENCH
					//con = DriverManager.getConnection(your url, "your username", "your password");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema01","root" ,"laggingsql34");        	
					stmt = con.createStatement();
					
			        //1)ADD DATA TO THE TABLE WITH A NEW ROW
			        System.out.println(string01);
					stmt.executeUpdate(string01);
			        
					
					System.out.println("The program has executed");
					stmt.close();
					con.close();
					
					} catch(SQLException ex){
						System.err.println("SQLException: " + ex.getMessage());
					}
				
				//END OF JDBC RELATED CONTENT----------------------------------------------------------------
				//-------------------------------------------------------------------------------------------
	}
	
	
	
	
	public void setAdminUsernames(){
		adminUsernames.add("Aidan");
		adminUsernames.add("Joe");
		adminUsernames.add("Kim");
		adminUsernames.add("Martin");
		
	}
	

	public void setAdminPasswords(){
		adminPasswords.add("Aidan");
		adminPasswords.add("Joe");
		adminPasswords.add("Kim");
		adminPasswords.add("Martin");
		
	}
	
	public void setTeacherStaffUsernames(){
		teacherStaffUsernames.add("Paul");
		teacherStaffUsernames.add("Ruth");
		teacherStaffUsernames.add("Edel");
		teacherStaffUsernames.add("Laura");
		
	}
	
	
	public void setTeacherStaffPasswords(){
		teacherStaffPasswords.add("Paul");
		teacherStaffPasswords.add("Ruth");
		teacherStaffPasswords.add("Edel");
		teacherStaffPasswords.add("Laura");
	}
	 
	
	public void setStudentUsernames(){
		studentUsernames.add("Mark");
		studentUsernames.add("Catriona");
		studentUsernames.add("Ryan");
		studentUsernames.add("Gavin");
		
	}
	
	
	public void setStudentPasswords(){
		studentPasswords.add("Mark");
		studentPasswords.add("Catriona");
		studentPasswords.add("Ryan");
		studentPasswords.add("Gavin");
	}
	
	
	public void setBiologyQuestions(){
		biologyQuestions.add("What is an ameba?");
		biologyQuestions.add("What is an Elephant");
		biologyQuestions.add("What is an Donkey?");
		biologyQuestions.add("What is an Lizard?");
		biologyQuestions.add("What is an Mouse?");
		
	}
	
	public void setChemistryQuestions(){
		chemistryQuestions.add("What is chlorine?");
		chemistryQuestions.add("What is Hydrogen?");
		chemistryQuestions.add("What is Carbon?");
		chemistryQuestions.add("What is an Acid?");
		chemistryQuestions.add("What is an Alkalie?");
	}
	
	public void setPhysicsQuestios(){
		physicsQuestions.add("What is a vector?");
		physicsQuestions.add("What is speed?");
		physicsQuestions.add("What is gravity?");
		physicsQuestions.add("What is acceleration?");
		physicsQuestions.add("What is an electron?");
	}
	
	
	public ArrayList<String> getStudentUserNames(){
		return studentUsernames;
	}

	public ArrayList<String> getStudentPasswords(){
		return studentPasswords;
	}
	public ArrayList<String> getAdminUserNames(){
		return adminUsernames;
	}
	public ArrayList<String> getAdminPasswords(){
		return adminPasswords;
	}
	
	public ArrayList<String> getTeacherUserNames(){
		return teacherStaffUsernames;
	}
	public ArrayList<String> getTeacherPasswords(){
		return teacherStaffPasswords;
	}
	
	public ArrayList<String> getBiologyQuestions(){
		return biologyQuestions;
	}
	
	public ArrayList<String> getPhysicsQuestions(){
		return physicsQuestions;
	}
	
	public ArrayList<String> getChemistryQuestions(){
		return chemistryQuestions;
	}
}
