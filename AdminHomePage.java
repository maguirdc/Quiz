package quiz;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 * Class to create the AdminHomepage GUI
 */
public class AdminHomePage {

	/**
	 * Setting the Buttons
	 */
	Button createMemberAdminPageButton, editMemberAdminPageButton, deleteMemberAdminPageButton, createTeacherButton,
			createStudentButton, back;

	/**
	 * Constructor
	 * @param homeStage
	 */
	public AdminHomePage(Stage homeStage) {

		setScene(homeStage);
		homeStage.show();

	}

	// 2
	private Stage setScene(Stage homeStage) {

		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(homeStage));

		Scene scene = new Scene(border, BorderSize.HEIGHT.getValue(), BorderSize.WIDTH.getValue());

		border.setCenter(display(homeStage));

		homeStage.setScene(scene);

		return homeStage;

	}

	// 3
	private HBox title() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15));
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: lightgrey;");

		Label title = new Label("Admin Home Page");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}

	// 4
	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 550));
		hbox.setSpacing(10);

		back = new Button("Log Out");
		back.setPrefSize(150, 18);
		
		back.setOnAction(e -> back(e, homeStage));

		hbox.getChildren().addAll(back);

		return hbox;
	}

	// 5
	private GridPane display(Stage homeStage) {

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);

		// instantiations

		createTeacherButton = new Button("Create Teacher");
		createTeacherButton.setPrefSize(200, 60);
		createStudentButton = new Button("Create Student");
		createStudentButton.setPrefSize(200, 60);
		
		createTeacherButton.setOnAction(e -> CreateTeacherButtonEventHandler(e, homeStage));
		createStudentButton.setOnAction(e -> CreateStudentButtonEventHandler(e, homeStage));

		// createMemberAdminPageButton = new Button("Create Member");
		// createMemberAdminPageButton.setPrefSize(150, 18);
		// editMemberAdminPageButton = new Button("Edit Member");
		// editMemberAdminPageButton.setPrefSize(150, 18);
		// deleteMemberAdminPageButton = new Button("Delete Member");
		// deleteMemberAdminPageButton.setPrefSize(150, 18);
		// move

		grid.add(createTeacherButton, 0, 1);
		grid.add(createStudentButton, 1, 1);
		// grid.add(createMemberAdminPageButton, 0, 0);
		// grid.add(editMemberAdminPageButton, 0, 1);
		// grid.add(deleteMemberAdminPageButton, 0, 2);

		return grid;

	}

	/*
	private void WireEvents(Stage homeStage) {
		createMemberAdminPageButton.setOnAction(e ->
		CreateMemberButtonEventHandler(e, homeStage));
		editMemberAdminPageButton.setOnAction(e ->
		EditMemberButtonEventHandler(e, homeStage));
		deleteMemberAdminPageButton.setOnAction(e ->
		DeleteMemberButtonEventHandler(e, homeStage));
		back.setOnAction(e -> back(e, homeStage));
	}
*/
	private void CreateTeacherButtonEventHandler(ActionEvent event, Stage HomeStage) {
		CreateTeacher createTeacherDisplay = new CreateTeacher(HomeStage);
		createTeacherDisplay.getClass();
	}

	private void CreateStudentButtonEventHandler(ActionEvent event, Stage HomeStage) {
		CreateStudent createStudentDisplay = new CreateStudent(HomeStage);
		createStudentDisplay.getClass();
	}
	
	/*
	 * private void CreateMemberButtonEventHandler(ActionEvent event, Stage
	 * homeStage) { CreateMember createMemberDisplayPage = new
	 * CreateMember(homeStage); createMemberDisplayPage.getClass(); }
	 * 
	 * private void EditMemberButtonEventHandler(ActionEvent event, Stage
	 * homeStage) { EditMember editMemberDisplayPage = new
	 * EditMember(homeStage); editMemberDisplayPage.getClass(); }
	 * 
	 * private void DeleteMemberButtonEventHandler(ActionEvent event, Stage
	 * homeStage) { DeleteMember deleteMemberDisplayPage = new
	 * DeleteMember(homeStage); deleteMemberDisplayPage.getClass(); }
	 */

	private void back(ActionEvent event, Stage homeStage) {
		HomeScreen homeScreenDisplay = new HomeScreen();
		homeScreenDisplay.start(homeStage);
	}

}