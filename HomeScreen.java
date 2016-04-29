package quiz;

import quiz.CreateStudent;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HomeScreen extends Application {

	Button loginHomePageButton, registerHomePageButton;
	//adminRegisterButton
	RadioButton teacherRadioButton, adminRadioButton, studentRadioButton;
	TextField userNameHomePageInput, passwordHomePageInput;
	Label userNameHomePageInputLabel, passwordHomePageInputLabel;
	SearchSQL searchSQL = new SearchSQL();
	
	@Override
	public void start(Stage homeStage) {
		// TODO Auto-generated method stub
		
		setScene(homeStage);

		//createcontrols(homeStage);
		WireEvents(homeStage);
		
		homeStage.setTitle("Home Screen");
		homeStage.show();
		
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
		//adminRegisterButton = new Button("Admin");
		//adminRegisterButton.setPrefSize(150, 18);
		
		//adminRegisterButton.setOnAction(e -> AdminHomePageEventHandler(e, homeStage));
		//registerHomePageButton.setOnAction(e -> RegistrationButtonEventHandler(e, homeStage));
		//loginHomePageButton.setOnAction(e -> LoginButtonEventHandler(e, homeStage));
		
		
		hbox.getChildren().addAll(registerHomePageButton,loginHomePageButton);

		return hbox;
	}

	private GridPane display(Stage homeStage) {

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);
		
		/*
		String imagePath = "resources/picture/yourImage.jpg";
	    Image image = new Image(imagePath);

	    ImageView imageView = new ImageView(image);
		*/

		
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
		System.out.println("Wire Events");
		//optionButtons
		registerHomePageButton.setOnAction(e -> RegistrationButtonEventHandler(e, homeStage));
		loginHomePageButton.setOnAction(e -> LoginButtonEventHandler(e, homeStage));
		//gridpane
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
		System.out.println("register");
		
		if(studentRadioButton.isSelected() ){
			
			CreateStudent displayCreateStudentPage = new CreateStudent(homeStage);
			displayCreateStudentPage.getClass();	
		}
		
		if(teacherRadioButton.isSelected() ){
			
			CreateTeacher displayCreateTeacherPage = new CreateTeacher(homeStage);
			displayCreateTeacherPage.getClass();
		}
		
		if(adminRadioButton.isSelected() ){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Admin Register");
			alert.setHeaderText("Sorry Admin is unable to register");
			alert.setContentText("Try to login instead");
			alert.showAndWait();
			//AdminHomePage displayAdminPage = new AdminHomePage(homeStage);
			//displayAdminPage.getClass();
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
	
			
		if(adminRadioButton.isSelected()){
			
			ArrayList <AdminLoginCred> adminLoginList = new ArrayList<>();
			adminLoginList = searchSQL.searchAdminLogin();
			System.out.println("Hello");
			
			for(int loop = 0; loop<adminLoginList.size();loop++){
				
				if(adminLoginList.get(loop).getUsername().equals(userName) && adminLoginList.get(loop).getPassword().equals(password)){
					AdminHomePage displayAdminHomePageScreen = new AdminHomePage(homeStage);
					displayAdminHomePageScreen.getClass();
					
					System.out.println("Admin is correct");
				}
			}	
		}
		if(teacherRadioButton.isSelected()){
			
				ArrayList <TeacherLoginCred> teachLoginList = new ArrayList<>();
				teachLoginList = searchSQL.searchTeachLogin();
				System.out.println("in teacherRadio");
				
				for(int loop = 0; loop<teachLoginList.size();loop++){
					
					if((teachLoginList.get(loop).getUsername().equals(userName)) && (teachLoginList.get(loop).getPassword().equals(password))){
						
						GUIQuizOverview displayGUIQuizScreen = new GUIQuizOverview(homeStage);
						displayGUIQuizScreen.getClass();
						
						System.out.println("Teacher is correct");
					}
				}
			}		

		if(studentRadioButton.isSelected()){
			
			ArrayList <PupilLoginCred> pupilLoginList = new ArrayList<>();
			pupilLoginList = searchSQL.searchPupilLogin();
			System.out.println("in pupilRadio");
			
			for(int loop = 0; loop<pupilLoginList.size();loop++){
				
				if(pupilLoginList.get(loop).getUsername().equals(userName) && pupilLoginList.get(loop).getPassword().equals(password)){
					StudentQuizOverview displayStudentOverview = new StudentQuizOverview(homeStage);
					displayStudentOverview.getClass();
					
					System.out.println("Teacher is correct");
				}
			}
		}
	
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
