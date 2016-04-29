package quiz;

public class Profile extends Guardian {

	private String pupil_id; 
	private String pupilFirstName;
	private String pupilLastName;
	private String pupilEmail;
	

	public Profile(){
		
	}
	
	public Profile(String id, String firstName, String lastName, String email, String mobileNumber, String pupil_id, String pupilFirstName, String pupilLastName, String pupilEmail) {
		super(id, firstName, lastName, email, mobileNumber);
		this.pupil_id = pupil_id;
		this.pupilFirstName = pupilFirstName;
		this.pupilLastName = pupilLastName;
		this.pupilEmail = pupilEmail;
	}



	public String getPupil_id() {
		return pupil_id;
	}

	public void setPupil_id(String pupil_id) {
		this.pupil_id = pupil_id;
	}

	public String getPupilFirstName() {
		return pupilFirstName;
	}

	public void setPupilFirstName(String pupilFirstName) {
		this.pupilFirstName = pupilFirstName;
	}

	public String getPupilLastName() {
		return pupilLastName;
	}

	public void setPupilLastName(String pupilLastName) {
		this.pupilLastName = pupilLastName;
	}

	public String getPupilEmail() {
		return pupilEmail;
	}

	public void setPupilEmail(String pupilEmail) {
		this.pupilEmail = pupilEmail;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return super.getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		super.setFirstName(firstName);
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return super.getLastName();
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		super.setLastName(lastName);
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		super.setEmail(email);
	}

	@Override
	public String getMobileNumber() {
		// TODO Auto-generated method stub
		return super.getMobileNumber();
	}

	@Override
	public void setMobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		super.setMobileNumber(mobileNumber);
	}

}
