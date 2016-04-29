package quiz;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/*
 * Class to populate CreateTeacher GUI
 */
public class CreateTeacher {

	Button createTeacherButton, back;
	TextField staffIDinput, staffFirstName, staffLastName, staffPosition, staffAccessLevel, staffPasswordInput,
			staffEmailAddressInput, userContactNumberInput;
	Label staffIDLabel, staffFirstNameLabel, staffLastNameLabel, staffPositionLabel, staffAccessLevelLabel,
			userPasswordLabel, staffEmailAddressLabel, userContactNumberLabel;

	// constructor
	public CreateTeacher(Stage homeStage) {

		setScene(homeStage);
		homeStage.show();
	}

	// Set the scene to contain a BorderPane
	private Stage setScene(Stage homeStage) {

		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(homeStage));

		Scene scene = new Scene(border, BorderSize.HEIGHT.getValue(), BorderSize.WIDTH.getValue());

		border.setCenter(display(homeStage));

		homeStage.setScene(scene);

		return homeStage;

	}

	// Set the top of the BorderPane with a title
	private HBox title() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15));
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: lightgrey;");

		Label title = new Label("Create New Staff Member");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}
	//Set the bottom of the BorderPane to display option buttons
	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 550));
		hbox.setSpacing(10);

		back = new Button("Back");
		back.setPrefSize(150, 18);
		back.setOnAction(e -> BackButtonEventHandler(e, homeStage));

		hbox.getChildren().addAll(back);

		return hbox;
	}

	//Set the main display to a GridPane which contains different nodes
	private GridPane display(Stage homeStage) {

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);

		createTeacherButton = new Button("Create Teacher");
		createTeacherButton.setPrefSize(200, 20);

		staffIDLabel = new Label("Staff ID: ");
		staffIDinput = new TextField();
		staffIDinput.setMaxWidth(200);

		staffPosition = new TextField();
		staffPosition.setMaxWidth(200);
		staffPositionLabel = new Label("Staff Position: ");

		staffAccessLevel = new TextField();
		staffAccessLevel.setMaxWidth(200);
		staffAccessLevelLabel = new Label("Staff Access Level: ");

		staffFirstName = new TextField();
		staffFirstName.setMaxWidth(200);
		staffFirstNameLabel = new Label("First Name: ");

		staffLastName = new TextField();
		staffLastName.setMaxWidth(200);
		staffLastNameLabel = new Label("Last Name: ");

		userPasswordLabel = new Label("User Password: ");
		staffPasswordInput = new TextField();
		staffPasswordInput.setMaxWidth(200);

		staffEmailAddressLabel = new Label("User Email Address: ");
		staffEmailAddressInput = new TextField();
		staffEmailAddressInput.setMaxWidth(200);

		userContactNumberLabel = new Label("User Contact No: ");
		userContactNumberInput = new TextField();
		userContactNumberInput.setMaxWidth(200);

		createTeacherButton.setOnAction(e -> CreateTeacherButtonEventHandler(e, homeStage));

		grid.add(staffIDLabel, 0, 0);
		grid.add(staffIDinput, 1, 0);
		grid.add(staffPositionLabel, 0, 1);
		grid.add(staffPosition, 1, 1);
		grid.add(staffAccessLevelLabel, 0, 2);
		grid.add(staffAccessLevel, 1, 2);
		grid.add(staffFirstNameLabel, 0, 3);
		grid.add(staffFirstName, 1, 3);
		grid.add(staffLastNameLabel, 0, 4);
		grid.add(staffLastName, 1, 4);
		grid.add(userPasswordLabel, 0, 5);
		grid.add(staffPasswordInput, 1, 5);
		grid.add(staffEmailAddressLabel, 0, 6);
		grid.add(staffEmailAddressInput, 1, 6);
		grid.add(userContactNumberLabel, 0, 7);
		grid.add(userContactNumberInput, 1, 7);
		grid.add(createTeacherButton, 1, 10, 1, 2);

		return grid;

	}

	//handler for Create button click
	private void CreateTeacherButtonEventHandler(ActionEvent event, Stage HomeStage) {
		boolean validFieldEntries;
		boolean staffExists = true;
		ArrayStorage myArrayStorage = new ArrayStorage();
		validation userValidation = new validation();
		//retrieve the input from each text field 
		validFieldEntries = userValidation.checkTeacherCreationFields(staffIDinput.getText().toString(),
				staffFirstName.getText().toString(), staffLastName.getText().toString(),
				staffPosition.getText().toString(), staffAccessLevel.getText().toString(),
				staffPasswordInput.getText().toString(), staffEmailAddressInput.getText().toString());

		//check if any of the inputs are empty
		if (validFieldEntries == false) {
			//if empty display dialog box
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Staff Registration");
			alert.setHeaderText("Sorry all data fields must be filled in");
			alert.showAndWait();

			staffExists = userValidation.staffMemberExistingForAdding(staffIDinput.getText(),
					staffPasswordInput.getText());
			//if not empty add input to database
		} else {
			myArrayStorage.addStaffToDatabase(staffIDinput.getText().toString(), staffFirstName.getText().toString(),
					staffLastName.getText().toString(), staffPosition.getText().toString(),
					staffAccessLevel.getText().toString(), staffPasswordInput.getText().toString(),
					staffEmailAddressInput.getText().toString());

			//display dialog box
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Staff Registration");
			alert.setHeaderText("Staff Created");
			alert.showAndWait();
			//navigate back to home page
			AdminHomePage adminHomePageDisplay = new AdminHomePage(HomeStage);
			adminHomePageDisplay.getClass();
		}

		//check if staff already exists
		if (staffExists == false) {
			//display dialog box
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Staff Registration");
			alert.setHeaderText("Staff Created");
			alert.showAndWait();
		}
	}

	//handles the event for clicking back button
		private void BackButtonEventHandler(ActionEvent event, Stage HomeStage) {
			//navigates back to home page
			AdminHomePage adminHomePageDisplay = new AdminHomePage(HomeStage);
			adminHomePageDisplay.getClass();
		}


}