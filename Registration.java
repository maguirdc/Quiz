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

public class Registration {

	VBox registrationPage;
	Button registrationPageButton, homePageButton;
	TextField registrationPageuserNameInput, registrationPagepasswordInput, registrationPageuserClearanceLevel;
	Label registrationPageuserNameInputLabel, registrationPagePasswordInputLabel,
			registrationPageuserClearanceLevelLabel;

	public Registration(Stage HomeStage) {

		createControls(HomeStage);
		WireEvents(HomeStage);
		HomeStage.show();

	}

	private void WireEvents(Stage HomeStage) {
		registrationPageButton.setOnAction(e -> RegistrationPageButtonEventHandler(e));
		homePageButton.setOnAction(e -> HomePageButtonEventHandler(e, HomeStage));

	}

	private void RegistrationPageButtonEventHandler(ActionEvent event) {

		addUsers registerAddUsers = new addUsers();
		int userClearanceLevel = Integer.parseInt(registrationPageuserClearanceLevel.getText());
		registerAddUsers.addUserstoStorage(registrationPageuserNameInput.getText(),
				registrationPagepasswordInput.getText(), userClearanceLevel);

	}

	private void HomePageButtonEventHandler(ActionEvent event, Stage HomeStage) {

		HomeScreen homeScreenDisplay = new HomeScreen();
		homeScreenDisplay.getClass();

	}

	private void createControls(Stage HomeStage) {
		registrationPage = new VBox(10);
		registrationPage.setAlignment(Pos.CENTER);
		registrationPageButton = new Button("Register");
		homePageButton = new Button("Home Page");
		registrationPageuserNameInput = new TextField();
		registrationPageuserNameInput.setMaxWidth(200);
		registrationPageuserNameInputLabel = new Label("User Name:");
		registrationPagepasswordInput = new TextField();
		registrationPagepasswordInput.setMaxWidth(200);
		registrationPagePasswordInputLabel = new Label("Password:");
		registrationPageuserClearanceLevel = new TextField();
		registrationPageuserClearanceLevel.setMaxWidth(200);
		registrationPageuserClearanceLevelLabel = new Label("User Clearance Level");

		registrationPage.getChildren().addAll(registrationPageuserNameInputLabel, registrationPageuserNameInput,
				registrationPagePasswordInputLabel, registrationPagepasswordInput,
				registrationPageuserClearanceLevelLabel, registrationPageuserClearanceLevel, registrationPageButton,
				homePageButton);
		HomeStage.setScene(new Scene(registrationPage, 400, 400));
		HomeStage.setTitle("Registration Page");
	}

}
