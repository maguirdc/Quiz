package quiz;

public class TeacherLoginCred {
	
	private String username; 
	private String accessLevel; 
	private String password;
	
	//default constructor
	public TeacherLoginCred (){
	}
	
	//constructor
	public TeacherLoginCred (String username, String password, String accessLevel) {
		this.setUsername(username);
		this.setAccessLevel(accessLevel);
		this.setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
