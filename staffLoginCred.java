package quiz;

public class staffLoginCred {
	
	private String username; 
	private String accessLevel; 
	private String password;
	
	//default constructor
	public staffLoginCred(){
	}
	
	//constructor
	public staffLoginCred(String username, String password, String accessLevel) {
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
