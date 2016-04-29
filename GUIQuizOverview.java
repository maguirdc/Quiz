package quiz;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUIQuizOverview {

	Label selectQuizTitle;
	TextField search;
	Button newQuizTitle, selectBt, cancel, enrollPupil, send, report, submitBt, getGuardian, searchPupil;
	ComboBox<String> quizCombo;
	Label pupilNameTitle, QuizID, BiologyID;
	TableView<Quiz> quizList;

	ObservableList<Quiz> data;
	QuizRepository retrieveQuizes = new QuizRepository();

	// SearchSQL searchSQL = new SearchSQL();
	ArrayList<Quiz> listOfQuizes = new ArrayList<>();

	// 1
	public GUIQuizOverview(Stage homeStage) {

		setScene(homeStage);
		// WireEvents(homeStage);
		homeStage.setTitle("Teacher Overview");
		homeStage.show();

	}

	private Stage setScene(Stage homeStage) {

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

		Label title = new Label("Quiz");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}

	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 250));
		hbox.setSpacing(10);

		newQuizTitle = new Button("Create New Quiz");
		newQuizTitle.setPrefSize(150, 18);

		enrollPupil = new Button("Pupil Overview");
		enrollPupil.setPrefSize(150, 18);

		cancel = new Button("Cancel");
		cancel.setPrefSize(100, 18);

		hbox.getChildren().addAll(newQuizTitle, enrollPupil, cancel);

		newQuizTitle.setOnAction(e -> createNew(homeStage));
		enrollPupil.setOnAction(e -> enrollPupil(homeStage));

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

		// 2)Initialisations

		// Automatic Action

		listOfQuizes = retrieveQuizes.getAllQuizzes();

		data = FXCollections.observableArrayList(listOfQuizes);

		quizList = new TableView<Quiz>();
		quizList.setPrefSize(700, 500);
		quizList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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

		grid.add(quizList, 0, 2, 6, 6);
		
		quizList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
		quizList.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	System.out.println("double click");
					searchQuizzes(homeStage, newSelection.getId());                  
		        }
		    }
		});
			}
		});

		/*
		quizList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {

				selectBt.setDisable(false);
				selectBt.setOnAction(e -> searchQuizzes(homeStage, newSelection.getId()));

				System.out.println("single click working");

			}
		});
		*/

		return grid;
	}

	public void cancel(Stage homeStage) {

		HomeScreen homeScreen = new HomeScreen();
		homeScreen.start(homeStage);
	}

	private void createNew(Stage homeStage) {

		GUINewQuiz newQuiz = new GUINewQuiz(homeStage);
		newQuiz.getClass();
	}

	private void enrollPupil(Stage homeStage) {

		GUIPupilOverview ep = new GUIPupilOverview(homeStage);
		ep.getClass();
	}

	public void searchQuizzes(Stage homeStage, String id) {

		// Query to get quiz based on id
		Quiz myQuiz = new Quiz();
		ArrayList<Quiz> myQuizList = new ArrayList<>();

		myQuizList = retrieveQuizes.retrieveAllQuizzes();
		System.out.println("myQuizList: " + myQuizList.get(0).getId().toString());

		for (int x = 0; x < myQuizList.size(); x++) {

			if (myQuizList.get(x).getId().equals(id)) {
				System.out.println("contains an id");
				myQuiz = myQuizList.get(x);
			}
		}

		System.out.println("myQuiz.getID(): " + myQuiz.getId());

		GUIEditDeleteQuiz newStage = new GUIEditDeleteQuiz(homeStage, myQuiz);
		newStage.getClass();

	}
}
