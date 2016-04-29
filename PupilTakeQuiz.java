package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PupilTakeQuiz {
	
	private RetrieveQuiz myRe;

	//public Quiz newQuiz;
	public Label quizNameTitle, quizCatTitle, addQuestionTitle, questionCountTitle;
	
	public Quiz currentStudentQuiz = new Quiz();
	
	public Label quizQuestionLabel,anwserOptionOneLabel,anwserOptionTwoLabel,
	anwserOptionThreeLabel,anwserOptionFourLabel,anwserOptionFiveLabel;
	
	public TextField pupilTxtfSearch,quizQuestion,anwserOptionOne,
	anwserOptionTwo,anwserOptionThree,anwserOptionFour,anwserOptionFive;
	
	public Button createNewQ, selectExistingQ, pupilInsertBtn,pupilBtnSearch;
	
	public Label pupilLabelAvg;
	public TextField pupilGiveID,pupilPutAvg;
	public Button pupilBtnGetAvg ;
	
	public PupilTakeQuiz(Stage homeStage, Quiz topicQuiz) {

		Stage newStage = setScene(homeStage, topicQuiz);
		newStage.show();

	}

	private Stage setScene(Stage homeStage,Quiz topicQuiz) {

		int questionCount = 0;

		homeStage.setTitle("Pupil Overview");

		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(homeStage));

		Scene scene = new Scene(border, 800, 500);

		border.setCenter(display(questionCount, homeStage));

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
		hbox.setPadding(new Insets(15, 20, 25, 450));
		hbox.setSpacing(10);

		Button nextQuestion, submit, cancel;
	
		nextQuestion = new Button("Next Question");
		nextQuestion.setPrefSize(100, 18);
		//nextQuestion.setOnAction(e -> createAllPupilsOverview(homeStage));
		
		submit = new Button("Submit");
		submit.setPrefSize(100, 18);
		
		//System.out.println("Cancel");
		cancel = new Button("Cancel");
		cancel.setPrefSize(100, 18);
		cancel.setOnAction(e -> cancel(homeStage));
		
		hbox.getChildren().addAll(nextQuestion,submit,cancel);
		
		return hbox;
	}

	private GridPane display(int num, Stage homeStage) {

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);

	    quizQuestionLabel = new Label("Question");
		quizQuestion = new TextField(null);
		quizQuestion.setPrefWidth(400);
		
		anwserOptionOneLabel = new Label("Option 1:");
		anwserOptionOne = new TextField(null);
		anwserOptionOne.setPrefWidth(200);
		
		anwserOptionTwoLabel = new Label("Option 2:");
		anwserOptionTwo = new TextField(null);
		
		anwserOptionThreeLabel = new Label("Option 3:");
		anwserOptionThree = new TextField(null);
		
		anwserOptionFourLabel = new Label("Option 4");
		anwserOptionFour= new TextField(null);
		
		anwserOptionFiveLabel = new Label("Option 5");
		anwserOptionFive = new TextField(null);
		
		pupilInsertBtn = new Button("Insert Entity");
		
		pupilLabelAvg = new Label("Give Pupil ID:");
		pupilGiveID = new TextField(null);
		pupilBtnGetAvg = new Button("Get AVG");
		pupilPutAvg = new TextField(null);
			
		grid.add(quizQuestionLabel, 0, 1);
		grid.add(quizQuestion, 1, 1);
		
		grid.add(anwserOptionOneLabel, 0, 2);
		grid.add(anwserOptionOne, 1, 2);
		
		grid.add(anwserOptionTwoLabel, 0, 3);
		grid.add(anwserOptionTwo, 1, 3);
		
		grid.add(anwserOptionThreeLabel, 0, 4);
		grid.add(anwserOptionThree, 1, 4);
		
		grid.add(anwserOptionFourLabel, 0, 5);
		grid.add(anwserOptionFour, 1, 5);
		
		grid.add(anwserOptionFiveLabel, 0, 6);
		grid.add(anwserOptionFive, 1, 6);
	
		return grid;
	}

	private void populateNextSetOfQuestions(){
		
		currentStudentQuiz.getCategory();
		currentStudentQuiz.getClass();
		currentStudentQuiz.getId();
		//currentStudentQuiz.getName();
		
		for(int loop = 0; loop<currentStudentQuiz.getQuestionBank().size(); loop++){
			
			quizQuestion.setText(currentStudentQuiz.getQuestionBank().get(0).toString());
			
		}
		currentStudentQuiz.getQuestionBank();
	}
	
	private void cancel(Stage homeStage) {
		// TODO Auto-generated method stub
		StudentQuizOverview newStage = new StudentQuizOverview(homeStage);
		newStage.getClass();
	
	}
}
