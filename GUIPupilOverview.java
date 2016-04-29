package quiz;

import java.util.ArrayList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUIPupilOverview {
	
	public static String pupil_report_id;
	//Declarations
	Label pupilNameTitle;
	TextField pupilName = new TextField("");
	TableView<Pupil> pupilList;
	Button report, submitBt, cancel, getGuardian, searchPupil, confirmEnroll, confirmUnenroll;
	ObservableList<Pupil> data;
	
	SearchSQL searchSQL = new SearchSQL();
	EditSQL editSQL = new EditSQL();
	
	
	ArrayList <Pupil> pList = new ArrayList<>();
	
	public GUIPupilOverview(Stage homeStage) {

		Stage newStage = setScene(homeStage);
		newStage.show();

	}
	
	private Stage setScene(Stage homeStage) {


		homeStage.setTitle("Pupil Overview");

		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(homeStage));

		Scene scene = new Scene(border, BorderSize.HEIGHT.getValue(), BorderSize.WIDTH.getValue());

		border.setCenter(display(homeStage));

		homeStage.setScene(scene);

		return homeStage;

	}
	
	private HBox title() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15));
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: lightgrey;");

		Label title = new Label("Pupil Overview");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}
	
	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 200));
		hbox.setSpacing(10);

		
		submitBt = new Button("Save Changes");
		submitBt.setPrefSize(150, 18);
		cancel = new Button("Cancel");
		cancel.setPrefSize(100, 18);
		report = new Button("Progress Report");
		report.setPrefSize(150, 18);
		report.setDisable(true);

		hbox.getChildren().addAll(cancel, report, submitBt);

		submitBt.setOnAction(e -> save(homeStage));
		cancel.setOnAction(e -> cancel(homeStage));
		
		

		return hbox;
	}
	
	@SuppressWarnings("unchecked")
	private GridPane display(Stage homeStage) {

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);

		//Initialisations
		//pupilName= new TextField("");
		pupilName.setPromptText("enter first name");
		
		
		confirmEnroll = new Button("Enroll Pupil");
		confirmEnroll.setPrefSize(130, 18);
		confirmEnroll.setVisible(false);
		
		//NEW CODE
		confirmUnenroll = new Button("Unenroll Pupil");
		confirmUnenroll.setPrefSize(130, 18);
		confirmUnenroll.setVisible(false);
		
		//Automatic Action
		
		pList = searchSQL.searchSQL(pupilName.getText());
		data = FXCollections.observableArrayList(pList);
		
		pupilNameTitle = new Label("Search for Pupil: ");
		searchPupil = new Button("Search");
		searchPupil.setOnAction(e -> update(homeStage));
		
		System.out.println("About to run editSQL");
		
		//use to edit SQL a certain table with a certain attribute with a certain value
		editSQL.editSQL("pupils", "pupil_id","PU000002",0);
		
		pupilList = new TableView<Pupil>();
		pupilList.setPrefSize(500, 500);
		pupilList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Pupil, String> pupilIdCol = new TableColumn<Pupil, String>("Pupil ID");
		pupilIdCol.setMinWidth(90);
		pupilIdCol.setCellValueFactory(new PropertyValueFactory<Pupil, String>("id"));
		
		TableColumn<Pupil, String> firstNameCol = new TableColumn<Pupil, String>("First Name");
		firstNameCol.setMinWidth(90);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Pupil, String>("firstName"));
		
		TableColumn<Pupil, String> lastNameCol = new TableColumn<Pupil, String>("Last name");
		lastNameCol.setMinWidth(90);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Pupil, String>("lastName"));
		
		TableColumn<Pupil, String> classIdCol = new TableColumn<Pupil, String>("Class ID");
		classIdCol.setMinWidth(90);
		classIdCol.setCellValueFactory(new PropertyValueFactory<Pupil, String>("classId"));
		
		TableColumn<Pupil, String> enrolledCol = new TableColumn<Pupil, String>("Enrolled");
		enrolledCol.setMinWidth(90);
		enrolledCol.setCellValueFactory(new PropertyValueFactory<Pupil, String>("enrolled"));
		
		
		pupilList.getColumns().addAll(pupilIdCol, firstNameCol, lastNameCol, classIdCol, enrolledCol);
		
		pupilList.setItems(data);
		
		
		pupilList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	
		    	report.setDisable(false);
		    	pupil_report_id = newSelection.getId();
		    	report.setOnAction(e -> viewReport(homeStage, newSelection.getId()));
		    	
		    	System.out.println("single click working");   
		    	
		    	if (newSelection.isEnrolled() == false){
		    		System.out.println("isNotEnrolled");
		    		confirmEnroll.setVisible(true);
		    		confirmUnenroll.setVisible(false);
		    		System.out.println(newSelection.isEnrolled());
		    	};
		    	
		    	if (newSelection.isEnrolled() == true){
		    		System.out.println("isEnrolled");
		    		confirmUnenroll.setVisible(true);
		    		confirmEnroll.setVisible(false);
		    		System.out.println(newSelection.isEnrolled());
		    	};
		    	
		    	confirmEnroll.setOnAction(e -> enroll(homeStage, newSelection));
		    	confirmUnenroll.setOnAction(e -> unenroll(homeStage, newSelection));
		    }
		});
		
		
		grid.add(pupilNameTitle, 0, 0, 1, 1);
		grid.add(searchPupil, 1, 1, 1, 1);
		grid.add(pupilName, 0, 1, 1, 1);
		grid.add(pupilList, 0, 2, 6, 6);
		grid.add(confirmEnroll, 2, 1, 1, 1);
		grid.add(confirmUnenroll, 2, 1, 1, 1);


		//pupilList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//System.out.println("selected: " + pupilList.getSelectionModel().getSelectedItem().isEnrolled());
		
		
		return grid;
	}
	
	//------------------------------------------------------------------------------------------------
	//----------------------
	
	public void check()throws NullPointerException{
		System.out.println("Entered check");
		pupilName.textProperty().addListener((observable,oldValue,newValue)-> {
			
			System.out.println("TextField change from" +  oldValue + " to " + newValue);
			pupilName.setText(newValue);
			System.out.println("Pupil get text to string: " + pupilName.getText().toString());
			});
	}
	
	
	public void update(Stage homeStage){
		System.out.println("Update");
		check();
		System.out.println("pupil name: " + pupilName.getText());
		pList = searchSQL.searchSQL(pupilName.getText());
		data = FXCollections.observableArrayList(pList);
		setScene(homeStage);
	}
	
	private void save(Stage homeStage) {

		GUIQuizOverview newStage = new GUIQuizOverview(homeStage);
		newStage.getClass();
	}

	private void cancel(Stage homeStage) {

		GUIQuizOverview newStage = new GUIQuizOverview(homeStage);
		newStage.getClass();
	}
	
	public void viewReport(Stage homeStage, String id) {
		GUIProgressReport newStage = new GUIProgressReport(homeStage);
		System.out.println(pupil_report_id);
		//pupil_report_id = id;
		newStage.getClass();
	}
	
	public void enroll(Stage homeStage, Pupil newSelection){
		newSelection.setEnrolled(true);
		//pList
		//insert new values into data (database)
		//draw down 
		//
		setScene(homeStage);
		
	}
	//NEW CODE
	public void unenroll(Stage homeStage, Pupil newSelection){
		newSelection.setEnrolled(false);
		setScene(homeStage);
		
	}
}
