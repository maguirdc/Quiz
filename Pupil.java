package quiz;

/*
 * Class to create the object Pupil
 * extends Person
 */
public class Pupil extends Person {

	private String classId;
	private String guardianId;
	private String password;
	private boolean enrolled;

	// default constructor
	public Pupil() {
		super();
	}

	//constructor with args
	public Pupil(String id, String firstName, String lastName, String email, String classId, String guardianId,
			String password, boolean enrolled) {
		super(id, firstName, lastName, email);
		this.classId = classId;
		this.guardianId = guardianId;
		this.password = password;
		this.enrolled = enrolled;
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

	public boolean isEnrolled() {
		return enrolled;
	}

	public void setEnrolled(boolean enrolled) {
		this.enrolled = enrolled;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getGuardianId() {
		return guardianId;
	}

	public void setGuardianId(String guardianId) {
		this.guardianId = guardianId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
