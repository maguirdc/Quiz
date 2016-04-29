package quiz;
import java.util.ArrayList;
import java.util.Arrays;

public class validation {

	ArrayStorage myArrayStorage = new ArrayStorage();
	
	ArrayList<String> adminUserNames = myArrayStorage.getAdminUserNames();
	
	ArrayList<String> adminPasswords = myArrayStorage.getAdminPasswords();
	
	
	
	
	ArrayList<String> teacherUserNames = myArrayStorage.getTeacherUserNames();
	
	ArrayList<String> teacherPasswords = myArrayStorage.getTeacherPasswords();
	
	ArrayList<String> studentUserNames = myArrayStorage.getStudentUserNames();
	
	ArrayList<String> studentPasswords = myArrayStorage.getStudentPasswords();
	
	public validation() {

	}

	public boolean checkTeacherCreationFields(String staffID, String staffFirstName, String staffLastName, String staffPosition, String staffAccessLevel, String staffPassword, String staffEmailAddress){
		if(staffID.equals("") || staffFirstName.equals("")|| staffLastName.equals("") || staffPosition.equals("") || staffAccessLevel.equals("") || staffPassword.equals("")|| staffEmailAddress.equals("")){
			return false;
		}else{
			return true;
		}	
	}
	
	public boolean checkPupilCreationFields(String pupilID, String pupilFirstName, String pupilLastName, String pupilClassID, String pupilGuardianID, String pupilEmailAddress){
		if(pupilID.equals("")|| pupilFirstName.equals("")|| pupilLastName.equals("") || pupilClassID.equals("") || pupilGuardianID.equals("")|| pupilEmailAddress.equals("")){
			return false;
		}else{
			return true;
		}
	}
	
	
	public boolean adminValidity(String userName, String password) {

		if (adminUserNames.contains(userName) &&  adminPasswords.contains(password)) {
			return true;
		} else {
			return true;
		}
	}

	public boolean studentValidity(String userName, String password) {

		if (Arrays.asList(studentUserNames).contains(userName) && Arrays.asList(studentPasswords).contains(password)) {
			return true;
		} else {
			return true;
		}

	}
	
	public boolean teacherValidity(String userName, String password){
		if(teacherUserNames.contains(userName)&& teacherPasswords.contains(password)){
			return true;
		}else{
			return true;
		}
	}

	public boolean staffMemberExistingForAdding (String userName, String password){
		if(teacherUserNames.contains(userName) && teacherPasswords.contains(password)){
			return true;
		}else{
			return true;
		}
	}
	
	public void addStaffMembersToStorage (String staffID, String firstName, String lastName, String staffPosition, String staffAccessLevel, String staffEmailAddress, String staffPassword){
		
		teacherUserNames.add(staffID);
		teacherPasswords.add(firstName);
		
	}
	
	public String retrieveStaffDetails (String userName, String password){
		
		String retrieveduserName = "" ;
		
		return retrieveduserName;
	}
	
public boolean isUserEnrolled(){
		
		//JDBC query 
		
		
		
		
		
		return false;
	
	}

	
	
}
