package quiz;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class addUsers {

	/**
	 * Creating an ArrayList to add the users to.
	 */
	private ArrayList<String> userDetails = new ArrayList<String>();

	/**
	 * Default Constructor
	 */
	public addUsers() {

	}

	/**
	 * COnstructor With Args
	 * 
	 * @param userName,
	 *            check the username isn't null
	 * @param password,
	 *            check the pasword isn't null
	 * @param clearanceLevel
	 *            check the clearence level is between 1&10
	 */
	public addUsers(String userName, String password, int clearanceLevel) {

		if (userName != null && password != null && clearanceLevel >= 1 && clearanceLevel <= 10) {
			userDetails.add(userName);
			userDetails.add(password);
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("User Not Added to Staff");
			alert.setContentText("Incorrect Clearance Level");
			alert.showAndWait();
		}
	}

	/**
	 * Method to add users to the Database
	 * @param userName
	 * @param password
	 * @param userClearanceLevel
	 */
	public void addUserstoStorage(String userName, String password, int userClearanceLevel) {
		if (userName != null && password != null && userClearanceLevel >= 1 && userClearanceLevel <= 10) {
			userDetails.add(userName);
			userDetails.add(password);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("User Added to Staff");
			alert.setContentText("Correct Clearance Level");
			alert.showAndWait();

		} else if (userName.equals(null) || password.equals(null) || userClearanceLevel < 1
				|| userClearanceLevel >= 10) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("User Not Added to Staff");
			alert.setContentText("Incorrect Clearance Level");
			alert.showAndWait();
		}

	}

}