package quiz;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StudentQuizOverview {
	
	//1)Declarations
	validation isStudentEnrolled = new validation();
	Label QuizID, BiologyID;
	TextField pupilName= new TextField("");
	TableView<Quiz> quizList;
	int flag = 0;
	
	Button physicsQuizSelectionButton, chemistryQuizSelectionButton, biologyQuizSelectionButton;
	Button cancel, takeQuiz;
	Quiz takenQuiz;
	
	ObservableList<Quiz> data;
	RetrieveQuiz retrieveQuizes = new RetrieveQuiz();
	
	//SearchSQL searchSQL = new SearchSQL();
	ArrayList <Quiz> listOfQuizes = new ArrayList<>();
	
	
	
	//constructor
	public StudentQuizOverview(Stage homeStage) {
		

		Stage newStage = setScene(homeStage);
		newStage.show();

	}
	
	private Stage setScene(Stage homeStage) {


		homeStage.setTitle("Pupil Overview");

		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(homeStage));

		Scene scene = new Scene(border, 800, 600);

		border.setCenter(display(homeStage, flag));

		homeStage.setScene(scene);

		return homeStage;

	}
	
	private HBox title() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15));
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: lightgrey;");
		Label title = new Label("Pupil Quiz Overview");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		hbox.getChildren().addAll(title);
		return hbox;
	}
	
	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 400));
		hbox.setSpacing(10);
		
		//initialisation
		cancel = new Button("Cancel");
		cancel.setPrefSize(100, 18);
		takeQuiz = new Button("Take Quiz");
		takeQuiz.setPrefSize(100, 18);
		takeQuiz.setDisable(true);
		
		
		cancel.setOnAction(e -> cancel(homeStage));
		//takeQuiz.setOnAction(e -> takeQuiz(homeStage));
		//takeQuiz(Stage homeStage, Quiz quizTopic){
		
		hbox.getChildren().addAll(cancel, takeQuiz);
		return hbox;
	}
	
	
	

	@SuppressWarnings("unchecked")
	private GridPane display(Stage homeStage, int flag) {
		
		//Quiz q1 = new Quiz("123", "Geography", "Physics", );
		
		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);
		
		//2)Initialisations
		
		//Automatic Action
		
		listOfQuizes = retrieveQuizes.getAllQuizzes();
	
		data = FXCollections.observableArrayList(listOfQuizes);
		
		quizList = new TableView<Quiz>();
		quizList.setPrefSize(700, 500);
		quizList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		chemistryQuizSelectionButton = new Button("Chemistry");
		chemistryQuizSelectionButton.setPrefSize(100, 18);
		physicsQuizSelectionButton = new Button("Physics");
		physicsQuizSelectionButton.setPrefSize(100, 18);
		biologyQuizSelectionButton = new Button("Biology");
		biologyQuizSelectionButton.setPrefSize(100, 18);
		
		
		
		//chemistryQuizSelectionButton.setOnAction(e -> retrieveQuizes.RetrieveBiology(cat));
		
		//biologyQuizSelectionButton.setOnAction(e -> retrieveQuizes.RetrieveBiology());
		//biologyQuizSelectionButton.setOnAction(e -> update(homeStage, quizTopic));
		
		//physicsQuizSelectionButton.setOnAction(e -> takeQuiz(homeStage, "Physics"));
		
		TableColumn<Quiz, String> subjectID = new TableColumn<Quiz, String>("Quiz ID");
		subjectID.setMinWidth(90);
		subjectID.setCellValueFactory(new PropertyValueFactory<Quiz, String>("id"));
			
		TableColumn<Quiz, String> dateSubmitted = new TableColumn<Quiz, String>("Date Submitted");
		dateSubmitted.setMinWidth(90);
		dateSubmitted.setCellValueFactory(new PropertyValueFactory<Quiz, String>("date_submitted"));
		
		TableColumn<Quiz, QuizType> quizID = new TableColumn<Quiz, QuizType>("Subject");
		quizID.setMinWidth(90);
		quizID.setCellValueFactory(new PropertyValueFactory<Quiz, QuizType>("category"));
		
		quizList.getColumns().addAll(subjectID, quizID, dateSubmitted);
		quizList.setItems(data);
		
		grid.add(chemistryQuizSelectionButton, 0, 0, 1, 1);
		grid.add(physicsQuizSelectionButton, 1, 0, 1, 1);
		grid.add(biologyQuizSelectionButton, 2, 0, 1, 1);
		grid.add(quizList, 0, 2, 6, 6);
		
		quizList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	
		    	takeQuiz.setDisable(false);
		    	takeQuiz.setOnAction(e -> takeQuiz(homeStage, newSelection.getId()));
		    	
		    	System.out.println("single click working");   
		    	
		    }
		});
		
		return grid;
	}
	
	
	
	
	private void takeQuiz(Stage homeStage, String id){
		
		//Query to get quiz based on id
		Quiz myQuiz = new Quiz();
		ArrayList <Quiz> myQuizList = new ArrayList<>();
		
		myQuizList = retrieveQuizes.retrieveAllQuizzes();	
		System.out.println("myQuizList: " + myQuizList.get(0).getId().toString()); 
		
		for(int x=0; x< myQuizList.size(); x++){
			
			if(myQuizList.get(x).getId().equals(id)){
				System.out.println("contains an id");
				myQuiz = myQuizList.get(x);
			}
		}
		
		System.out.println("myQuiz.getID(): " + myQuiz.getId());
		
		PupilTakeQuizScreen screen = new PupilTakeQuizScreen(homeStage, myQuiz);
		screen.getClass();
	}
	
	private void cancel(Stage homeStage) {
		// TODO Auto-generated method stub
		HomeScreen homeScreen = new HomeScreen();
		homeScreen.start(homeStage);
	
	}
	
	public void update(Stage homeStage, Quiz quizType){
		System.out.println("Retrieve");
		//retrieveQuizes.RetrieveBiology();
		//check();
		//listOfQuizes = retrieveQuizes.RetrieveBiology();
		//data = FXCollections.observableArrayList();
		System.out.println("Bug");
		flag = 1;
		setScene(homeStage);
	}
	
	public void check()throws NullPointerException{
		
		pupilName.textProperty().addListener((observable,oldValue,newValue)-> {
			
			System.out.println("TextField change from" +  oldValue + " to " + newValue);
			pupilName.setText(newValue);
			});
	}
}
	
	
	
//	table.setOnMousePressed(new EventHandler<MouseEvent>() {
//	    @Override 
//	    public void handle(MouseEvent event) {
//	        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
//	            System.out.println(table.getSelectionModel().getSelectedItem());                   
//	        }
//	    }
//	});
	
//	private void save(Stage homeStage) {
//
	//	GUIQuizOverview newStage = new GUIQuizOverview(homeStage);/
//		newStage.getClass();
//	}

//	private void cancel(Stage homeStage) {

//		GUIQuizOverview newStage = new GUIQuizOverview(homeStage);
//		newStage.getClass();
//	}
	
//	public void sendmail() {
//		SendEmail send = new SendEmail("mclarke46@qub.ac.uk", "Michael", "Mr. Jenkins", "ProgressReportPlaceholder");
//		send.send();
//	}
	
//	public void guardian(Stage homeStage){
//		GUIGuardianOverview g1 = new GUIGuardianOverview(homeStage);
//		g1.getClass();
//	}
