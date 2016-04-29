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

public class EditMember {

	VBox editMemberPage;
	Button editStaffButton, backButton, logOutHomeScreenButton;
	
	TextField userNameInput, userPasswordInput, userEmailAddressInput, userContactNumberInput, userSearchInput;
	Label userNameLabel, userPasswordLabel, userEmailAddressLabel, userContactNumberLabel, userSearchInputLabel;

	public EditMember(Stage HomeStage) {
		
		CreateControls(HomeStage);
		WireEvents(HomeStage);
		HomeStage.show();

	}

	private void CreateControls(Stage HomeStage){
		editMemberPage = new VBox(10);
		editMemberPage.setAlignment(Pos.CENTER);
		editStaffButton = new Button("Edit Teacher");
		userSearchInputLabel = new Label("User Search Details: ");
		userSearchInput = new TextField();
		userSearchInput.setMaxWidth(200);
		backButton = new Button("Back");
		userNameInput = new TextField();
		userNameInput.setMaxWidth(200);
		userPasswordInput = new TextField();
		userPasswordInput.setMaxWidth(200);
		userEmailAddressInput = new TextField();
		userEmailAddressInput.setMaxWidth(200);
		userContactNumberInput = new TextField();
		userContactNumberInput.setMaxWidth(200);
		userNameLabel = new Label("User Name: ");
		userPasswordLabel = new Label("User Password: ");
		userEmailAddressLabel = new Label("User Email Address: ");
		userContactNumberLabel = new Label("User Contact No: ");
		userNameLabel = new Label("User Name: ");
		logOutHomeScreenButton = new Button("Log Out");
		
		editMemberPage.getChildren().addAll(userNameLabel, userNameInput, userPasswordLabel, 
				userPasswordInput,userEmailAddressLabel, userEmailAddressInput, 
				userContactNumberLabel, userContactNumberInput, userSearchInputLabel, 
				userSearchInput, editStaffButton, backButton, logOutHomeScreenButton);
		HomeStage.setScene(new Scene(editMemberPage, 400, 400));
		HomeStage.setTitle("Edit Member");
	}
	
	private void WireEvents(Stage HomeStage) {
		editStaffButton.setOnAction(e -> EditStaffButtonEventHandler(e, HomeStage));
		backButton.setOnAction(e -> BackButtonEventHandler(e, HomeStage));
		logOutHomeScreenButton.setOnAction(e -> LogOutHomeScreenEventHandler(e, HomeStage));
	}

	private void EditStaffButtonEventHandler(ActionEvent event, Stage HomeStage) {

	}
	
	private void LogOutHomeScreenEventHandler(ActionEvent event, Stage HomeStage) {
		HomeScreen homesScreenDisplay = new HomeScreen();
		homesScreenDisplay.getClass();
	}
	
	
	private void BackButtonEventHandler(ActionEvent event, Stage HomeStage) {
		AdminHomePage adminHomePageDisplay = new AdminHomePage(HomeStage);
		adminHomePageDisplay.getClass();
	}

}