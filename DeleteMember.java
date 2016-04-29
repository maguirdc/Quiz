package quiz;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteMember {

	VBox deleteMemberPage;
	Button deleteStaffButton, findStaffButton, backButton, logOutHomeScreenButton;
	TextField userNameInput, userPasswordInput, userEmailAddressInput, userContactNumberInput, userSearchInput;
	Label userNameLabel, userPasswordLabel, userEmailAddressLabel, userContactNumberLabel, userSearchInputLabel;

	public DeleteMember(Stage HomeStage) {

		CreateControls(HomeStage);
		WireEvents(HomeStage);
		HomeStage.show();

	}

	private void WireEvents(Stage HomeStage) {

		findStaffButton.setOnAction(e -> SearchForStaffButtonEventHandler(e));
		deleteStaffButton.setOnAction(e -> DeleteStaffButtonEventHandler(e, HomeStage));
		backButton.setOnAction(e -> BackButtonEventHandler(e, HomeStage));
		logOutHomeScreenButton.setOnAction(e -> LogOutHomeScreenEventHandler(e, HomeStage));
	}

	private void CreateControls(Stage HomeStage) {
		deleteMemberPage = new VBox(10);
		deleteMemberPage.setAlignment(Pos.CENTER);
		deleteStaffButton = new Button("Delete Staff");
		findStaffButton = new Button("Retrieve Staff Member");
		userSearchInputLabel = new Label("User Search Details: ");
		userSearchInput = new TextField();
		userSearchInput.setMaxWidth(200);
		userNameInput = new TextField();
		userNameInput.setMaxWidth(200);
		userPasswordInput = new TextField();
		userPasswordInput.setMaxWidth(200);
		userEmailAddressInput = new TextField();
		userEmailAddressInput.setMaxWidth(200);
		userContactNumberInput = new TextField();
		userContactNumberInput.setMaxWidth(200);
		backButton = new Button("Back");
		userNameLabel = new Label("User Name: ");
		userPasswordLabel = new Label("User Password: ");
		userEmailAddressLabel = new Label("User Email Address: ");
		userContactNumberLabel = new Label("User Contact No: ");
		userNameLabel = new Label("User Name: ");
		logOutHomeScreenButton = new Button("Log Out");
		deleteMemberPage.getChildren().addAll(userNameLabel, userNameInput, userPasswordLabel, userPasswordInput,
				userEmailAddressLabel, userEmailAddressInput, userContactNumberLabel, userContactNumberInput,
				userSearchInputLabel, userSearchInput, findStaffButton, deleteStaffButton, backButton, logOutHomeScreenButton);
		HomeStage.setScene(new Scene(deleteMemberPage, 400, 500));
		HomeStage.setTitle("Delete Member");
	}

	private void DeleteStaffButtonEventHandler(ActionEvent event, Stage HomeStage) {

	}
	private void LogOutHomeScreenEventHandler(ActionEvent event, Stage HomeStage) {
		HomeScreen homesScreenDisplay = new HomeScreen();
		homesScreenDisplay.getClass();
	}


	private void SearchForStaffButtonEventHandler(ActionEvent event) {
		validation findUserValidation = new validation();
		findUserValidation.teacherValidity(userSearchInput.getText(), userPasswordInput.getText());
	}

	private void BackButtonEventHandler(ActionEvent event, Stage HomeStage) {
		AdminHomePage adminHomePageDisplay = new AdminHomePage(HomeStage);
		adminHomePageDisplay.getClass();

	}

}