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

public class CreateMember {

	VBox createMemberPage;
	Button createTeacherButton, createStudentButton, backButton, logOutHomeScreenButton;

	public CreateMember(Stage HomeStage) {

		CreateControls(HomeStage);
		WireEvents(HomeStage);
		HomeStage.show();

	}

	private void WireEvents(Stage HomeStage) {
		createTeacherButton.setOnAction(e -> CreateTeacherButtonEventHandler(e, HomeStage));
		createStudentButton.setOnAction(e -> CreateStudentButtonEventHandler(e, HomeStage));
		backButton.setOnAction(e-> BackButtonEventHandler(e, HomeStage));
		logOutHomeScreenButton.setOnAction(e-> LogOutHomeScreenEventHandler(e, HomeStage));
	}

	private void CreateTeacherButtonEventHandler(ActionEvent event, Stage HomeStage) {
		CreateTeacher createTeacherDisplay = new CreateTeacher(HomeStage);
		createTeacherDisplay.getClass();
	}

	private void CreateStudentButtonEventHandler(ActionEvent event, Stage HomeStage) {
		CreateStudent createStudentDisplay = new CreateStudent(HomeStage);
		createStudentDisplay.getClass();
	}
	
	private void LogOutHomeScreenEventHandler(ActionEvent event, Stage HomeStage) {
		HomeScreen homesScreenDisplay = new HomeScreen();
		homesScreenDisplay.getClass();
	}
	

	private void CreateControls(Stage HomeStage) {
		createMemberPage = new VBox(10);
		createMemberPage.setAlignment(Pos.CENTER);
		createTeacherButton = new Button("Create Teacher");
		createStudentButton = new Button("Create Student");
		backButton = new Button("Back");
		logOutHomeScreenButton = new Button("Log Out");
		createMemberPage.getChildren().addAll(createTeacherButton, createStudentButton, backButton, logOutHomeScreenButton);
		HomeStage.setScene(new Scene(createMemberPage, 400, 400));
		HomeStage.setTitle("Create Member");
	}

	private void BackButtonEventHandler(ActionEvent event, Stage HomeStage) {
		
		AdminHomePage adminHomePageDisplay = new AdminHomePage(HomeStage);
		adminHomePageDisplay.getClass();
		
	}
	
	
}