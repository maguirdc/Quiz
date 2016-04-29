package quiz;

import quiz.CreateStudent;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HomeScreen extends Application {

	Button loginHomePageButton, registerHomePageButton, adminRegisterButton;
	RadioButton teacherRadioButton, adminRadioButton, studentRadioButton;
	TextField userNameHomePageInput, passwordHomePageInput;
	Label userNameHomePageInputLabel, passwordHomePageInputLabel;
	PeopleRepository repository;
	
	@Override
	public void start(Stage homeStage) {
		// TODO Auto-generated method stub
		
		setScene(homeStage);

		//createcontrols(homeStage);
		WireEvents(homeStage);
		
		homeStage.setTitle("Home Screen");
		homeStage.show();
		
		try {
			repository = new PeopleRepository();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private Stage setScene(Stage homeStage) {
		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(homeStage));

		Scene scene = new Scene(border, 800, 500);

		border.setCenter(display(homeStage));

		homeStage.setScene(scene);

		return homeStage;
	}

	private HBox title() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15));
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: lightgrey;");

		Label title = new Label("Home Screen");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}

	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 250));
		hbox.setSpacing(10);
		
		registerHomePageButton = new Button("Register");
		registerHomePageButton.setPrefSize(150, 18);
		loginHomePageButton = new Button("Login");
		loginHomePageButton.setPrefSize(150, 18);
		adminRegisterButton = new Button("Admin");
		adminRegisterButton.setPrefSize(150, 18);
		
		adminRegisterButton.setOnAction(e -> AdminHomePageEventHandler(e, homeStage));
		registerHomePageButton.setOnAction(e -> RegistrationButtonEventHandler(e, homeStage));
		loginHomePageButton.setOnAction(e -> LoginButtonEventHandler(e, homeStage));
		
		hbox.getChildren().addAll(registerHomePageButton,loginHomePageButton, adminRegisterButton);

		return hbox;
	}

	private GridPane display(Stage homeStage) {

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);
		
		userNameHomePageInputLabel = new Label("User Name");
		userNameHomePageInput = new TextField();
		userNameHomePageInput.setPromptText("User Name: ");
				
		passwordHomePageInputLabel = new Label("Password");
		passwordHomePageInput = new TextField();
		passwordHomePageInput.setPromptText("User Password: ");
		passwordHomePageInput.setMaxWidth(200);
		
		teacherRadioButton = new RadioButton("Teacher");
		adminRadioButton = new RadioButton("Admin");
		studentRadioButton = new RadioButton("Student");
		
		userNameHomePageInput.setPromptText("User Name: ");
		userNameHomePageInput.setMaxWidth(200);

		grid.add(userNameHomePageInputLabel, 0, 0);
		grid.add(userNameHomePageInput, 1, 0);
		
		grid.add(passwordHomePageInputLabel, 0, 1);
		grid.add(passwordHomePageInput, 1, 1);
		
		grid.add(teacherRadioButton,1,5);
		grid.add(adminRadioButton,1,6);
		grid.add(studentRadioButton,1,7);

		return grid;
	}
	
	private void WireEvents(Stage homeStage) {
		registerHomePageButton.setOnAction(e -> RegistrationButtonEventHandler(e, homeStage));
		loginHomePageButton.setOnAction(e -> LoginButtonEventHandler(e, homeStage));
		teacherRadioButton.setOnAction(e -> teacherRadioButtonEventHandler(e));
		adminRadioButton.setOnAction(e -> adminRadioButtonEventHandler(e));
		studentRadioButton.setOnAction(e -> studentRadioButtonEventHandler(e));
	}

	private void AdminHomePageEventHandler(ActionEvent event, Stage homeStage) {
		if(adminRadioButton.isSelected() ){
			AdminHomePage displayAdminHomePageScreen = new AdminHomePage(homeStage);
			displayAdminHomePageScreen.getClass();
		}		
	}

	private void RegistrationButtonEventHandler(ActionEvent event, Stage homeStage) {
		
		if(studentRadioButton.isSelected() ){
			CreateStudent displayCreateStudentPage = new CreateStudent(homeStage);
			displayCreateStudentPage.getClass();
		}
				
	}
	private void adminRadioButtonEventHandler(ActionEvent event){
		teacherRadioButton.setSelected(false);
		studentRadioButton.setSelected(false);
	}
	
	private void teacherRadioButtonEventHandler(ActionEvent event){
		adminRadioButton.setSelected(false);
		studentRadioButton.setSelected(false);
	}
	
	private void studentRadioButtonEventHandler(ActionEvent event){
		adminRadioButton.setSelected(false);
		teacherRadioButton.setSelected(false);
	}
	
	private void LoginButtonEventHandler(ActionEvent event, Stage homeStage) {
		String userName = userNameHomePageInput.getText();
		String password = passwordHomePageInput.getText();
		
		if (adminRadioButton.isSelected() || teacherRadioButton.isSelected())
		{
			Staff staff = new Staff();
			staff.setEmail(userName);
			staff.setUser_password(password);
			
			Staff authenticatedStaff;
			try {
				authenticatedStaff = repository.authenicateStaff(staff);
				if (authenticatedStaff.getAccess_level().isEmpty()) 
				{
					// Add an alert in here that the username or password isn't recognised
					return;
				}
				if (authenticatedStaff.getAccess_level().toLowerCase().equals("admin"))
				{
					AdminHomePage displayAdminHomePageScreen = new AdminHomePage(homeStage);
					displayAdminHomePageScreen.getClass();

				} else if (authenticatedStaff.getAccess_level().toLowerCase().equals("teaching"))
				{
					GUIQuizOverview displayQuizOverView = new GUIQuizOverview(homeStage);
					displayQuizOverView.getClass();
				}} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		if (studentRadioButton.isSelected()) {
			Pupil pupil = new Pupil();
			pupil.setEmail(userName);
			pupil.setPassword(password);
			
			try {
				Pupil authenticatedPupil = repository.authenicatePupil(pupil);
				
				if (!authenticatedPupil.getEmail().isEmpty())
				{
					StudentQuizOverview displayQuizOverview = new StudentQuizOverview(homeStage);
					displayQuizOverview.getClass();
				} else {
					// Add an alert in here that the username or password isn't recognised
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
