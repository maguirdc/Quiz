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
 * Class to populate CreateStudent GUI
 */
public class CreateStudent {

	Button createStudentPageButton, backButton, logOutButton;
	TextField pupilIDInput, pupilFirstNameInput, pupilLastNameInput, pupilClassIDInput, pupilGuardianIDInput,
			pupilEmailAddressInput, pupilPasswordInput, pupilEnrollmentInput;
	Label pupilIDInputLabel, pupilFirstNameInputLabel, pupilLastNameInputLabel, pupilClassIdLabel, pupilGuardianIDLabel,
			pupilEmailAddressInputLabel, pupilPasswordLabel, pupilEnrollmentLabel, userPasswordLabel;

	//Constructor
	public CreateStudent(Stage homeStage) {

		setScene(homeStage);
		homeStage.show();

	}

	//Set the scene to contain a BorderPane
	private Stage setScene(Stage homeStage) {

		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(homeStage));

		Scene scene = new Scene(border, BorderSize.HEIGHT.getValue(), BorderSize.WIDTH.getValue());

		border.setCenter(display(homeStage));

		homeStage.setScene(scene);

		return homeStage;

	}

	//Set the top of the BorderPane with a title
	private HBox title() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15));
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: lightgrey;");

		Label title = new Label("Create New Student");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}

	//Set the bottom of the BorderPane to display option buttons
	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 550));
		hbox.setSpacing(10);

		backButton = new Button("Back");
		backButton.setPrefSize(150, 18);
		backButton.setOnAction(e -> BackButtonEventHandler(e, homeStage));

		hbox.getChildren().addAll(backButton);

		return hbox;
	}

	//Set the main display to a GridPane which contains different nodes
	private GridPane display(Stage homeStage) {

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);

		createStudentPageButton = new Button("Create Student: ");
		createStudentPageButton.setPrefSize(200, 20);

		pupilIDInputLabel = new Label("Pupil ID: ");
		pupilIDInput = new TextField();
		pupilIDInput.setMaxWidth(200);

		pupilFirstNameInput = new TextField();
		pupilFirstNameInput.setMaxWidth(200);
		pupilFirstNameInputLabel = new Label("Pupil First Name: ");

		pupilLastNameInput = new TextField();
		pupilLastNameInput.setMaxWidth(200);
		pupilLastNameInputLabel = new Label("Pupil Last Name: ");

		pupilClassIdLabel = new Label("Pupil Class ID: ");
		pupilClassIDInput = new TextField();
		pupilClassIDInput.setMaxWidth(200);

		pupilGuardianIDInput = new TextField();
		pupilGuardianIDInput.setMaxWidth(200);
		pupilGuardianIDLabel = new Label("Pupil Guardian ID: ");

		pupilEmailAddressInput = new TextField();
		pupilEmailAddressInput.setMaxWidth(200);
		pupilEmailAddressInputLabel = new Label("Pupil Email Address: ");
		
		userPasswordLabel = new Label("User Password: ");
		pupilPasswordInput = new TextField();
		pupilPasswordInput.setMaxWidth(200);

		createStudentPageButton.setOnAction(e -> CreateUserButtonEventHandler(e, homeStage));

		grid.add(pupilIDInputLabel, 0, 0);
		grid.add(pupilIDInput, 1, 0);
		grid.add(pupilFirstNameInputLabel, 0, 1);
		grid.add(pupilFirstNameInput, 1, 1);
		grid.add(pupilLastNameInputLabel, 0, 2);
		grid.add(pupilLastNameInput, 1, 2);
		grid.add(pupilClassIdLabel, 0, 3);
		grid.add(pupilClassIDInput, 1, 3);
		grid.add(pupilGuardianIDLabel, 0, 4);
		grid.add(pupilGuardianIDInput, 1, 4);
		grid.add(pupilEmailAddressInputLabel, 0, 5);
		grid.add(pupilEmailAddressInput, 1, 5);
		grid.add(userPasswordLabel, 0, 6);
		grid.add(pupilPasswordInput, 1, 6);
		grid.add(createStudentPageButton, 1, 8, 1, 2);

		return grid;

	}
	
	//Action handler for the button click
	private void CreateUserButtonEventHandler(ActionEvent event, Stage HomeStage) {
		ArrayStorage myArrayStorage = new ArrayStorage();

		//retrieve the input from each text field 
		boolean validFieldEntries;
		validation userValidation = new validation();
		validFieldEntries = userValidation.checkPupilCreationFields(pupilIDInput.getText().toString(),
				pupilFirstNameInput.getText().toString(), pupilLastNameInput.getText().toString(),
				pupilClassIDInput.getText().toString(), pupilGuardianIDInput.getText().toString(),
				pupilEmailAddressInput.getText().toString());

		//check if any of the inputs are empty
		if (validFieldEntries == false) {
			//if empty display dialog box
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Pupil Registration");
			alert.setHeaderText("Sorry you must fill in every value");
			alert.showAndWait();
			//if not empty add input to database
		} else {
			System.out.println("in the else statement");
			//pupilEnrollmentInput.setText(0);
			System.out.println("Able to set text");
			/*
			myArrayStorage.addStudentsToDatabase(pupilIDInput.getText().toString(),
					pupilFirstNameInput.getText().toString(), pupilLastNameInput.getText().toString(),
					pupilClassIDInput.getText().toString(), pupilGuardianIDInput.getText().toString(),
					pupilEmailAddressInput.getText().toString(), pupilPasswordInput.getText().toString(),
					pupilEnrollmentInput.getText().toString());
					*/
			myArrayStorage.addStudentsToDatabase(pupilIDInput.getText().toString(),
					pupilFirstNameInput.getText().toString(), pupilLastNameInput.getText().toString(),
					pupilClassIDInput.getText().toString(), pupilGuardianIDInput.getText().toString(),
					pupilEmailAddressInput.getText().toString(), pupilPasswordInput.getText().toString(),
					"0");
			
			//display dialog box
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Pupil Registration");
			alert.setHeaderText("Pupil Created");
			alert.showAndWait();
			//navigate back to home page
			AdminHomePage adminHomePageDisplay = new AdminHomePage(HomeStage);
			adminHomePageDisplay.getClass();
		}
	}
	//handles the event for clicking back button
	private void BackButtonEventHandler(ActionEvent event, Stage HomeStage) {
		//navigates back to home page
		/*AdminHomePage adminHomePageDisplay = new AdminHomePage(HomeStage);
		adminHomePageDisplay.getClass();*/
		
		HomeScreen homeScreen = new HomeScreen();
		homeScreen.start(HomeStage);
	}

}