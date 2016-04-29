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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PupilTakeQuizScreen {
	
	private int correctAnwserCounter = 0, questionCounter = 0;
	private RetrieveQuiz myRe;

	//public Quiz newQuiz;
	public Label quizNameTitle, quizCatTitle, addQuestionTitle, questionCountTitle;
	
	private RadioButton anwserOptionOneRadioButton, anwserOptionTwoRadioButton, anwserOptionThreeRadioButton; 
		
	public Label quizQuestionLabel,anwserOptionOneLabel,anwserOptionTwoLabel,
	anwserOptionThreeLabel,anwserOptionFourLabel,anwserOptionFiveLabel;
	
	private Answer anwserOne, anwserTwo, anwserThree;
	
	public TextField pupilTxtfSearch,quizQuestion,anwserOptionOne,
	anwserOptionTwo,anwserOptionThree,anwserOptionFour,anwserOptionFive;
	
	public Button createNewQ, selectExistingQ, pupilInsertBtn,pupilBtnSearch;
	
	public Label pupilLabelAvg;
	public TextField pupilGiveID,pupilPutAvg;
	public Button pupilBtnGetAvg ;
	
	public PupilTakeQuizScreen(Stage homeStage, Quiz currentStudentQuiz) {

		Stage newStage = setScene(homeStage, currentStudentQuiz);
		newStage.show();
		

	}

	private Stage setScene(Stage homeStage,Quiz currentStudentQuiz) {

		int questionCount = 0;

		homeStage.setTitle("Pupil Overview");

		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(homeStage, currentStudentQuiz));

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

	private HBox optionButtons(Stage homeStage, Quiz currentStudentQuiz) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 450));
		hbox.setSpacing(10);

		Button nextQuestion, submit, cancel;
	
		nextQuestion = new Button("Next Question");
		nextQuestion.setPrefSize(100, 18);
		nextQuestion.setOnAction(e -> populateNextSetOfQuestions(e,homeStage, currentStudentQuiz));
		
		//nextQuestion.setOnAction(e -> createAllPupilsOverview(homeStage));
		
		submit = new Button("Submit");
		submit.setPrefSize(100, 18);
		submit.setOnAction(e -> storePupilsFinalQuizScore(e));
		
		cancel = new Button("cancel");
		cancel.setPrefSize(100, 18);
		cancel.setOnAction(e -> cancel(homeStage));
		
		hbox.getChildren().addAll(nextQuestion,submit);
		
		return hbox;
	}

	private void cancel(Stage homeStage) {
		// TODO Auto-generated method stub
		//QuizTopic quizTopic;
		//PupilTakeQuiz newStage = new PupilTakeQuiz(homeStage,quizTopic);
		//newStage.getClass();

		
	}

	private GridPane display(int num, Stage homeStage) {

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);

	    quizQuestionLabel = new Label("Question");
		quizQuestion = new TextField();
		quizQuestion.setPrefWidth(400);
		quizQuestion.setEditable(false);
		
		anwserOptionOneLabel = new Label("Option 1:");
		anwserOptionOne = new TextField(null);
		anwserOptionOne.setPrefWidth(200);
		anwserOptionOne.setEditable(false);
		anwserOptionOneRadioButton = new RadioButton("Option 1:");
		anwserOptionOneRadioButton.setOnAction(e -> alterRadioButtonTwoAndThreeSelections(e));
		
		anwserOptionTwoLabel = new Label("Option 2:");
		anwserOptionTwo = new TextField(null);
		anwserOptionTwo.setEditable(false);
		anwserOptionTwoRadioButton = new RadioButton("Option 2:");
		anwserOptionTwoRadioButton.setOnAction(e -> alterRadioButtonOneAndThreeSelections(e));
		
		anwserOptionThreeLabel = new Label("Option 3:");
		anwserOptionThree = new TextField(null);
		anwserOptionThree.setEditable(false);
		anwserOptionThreeRadioButton = new RadioButton("Option 3:");
		anwserOptionThreeRadioButton.setOnAction(e -> alterRadioButtonOneAndTwoSelections(e));
			
		grid.add(quizQuestionLabel, 0, 1);
		grid.add(quizQuestion, 1, 1);
		
		grid.add(anwserOptionOneLabel, 0, 2);
		grid.add(anwserOptionOne, 1, 2);
		grid.add(anwserOptionOneRadioButton, 2, 2);
		
		grid.add(anwserOptionTwoLabel, 0, 3);
		grid.add(anwserOptionTwo, 1, 3);
		grid.add(anwserOptionTwoRadioButton, 2, 3);
		
		grid.add(anwserOptionThreeLabel, 0, 4);
		grid.add(anwserOptionThree, 1, 4);
		grid.add(anwserOptionThreeRadioButton, 2, 4);
		
		
		
		return grid;
	}

	private void alterRadioButtonTwoAndThreeSelections(ActionEvent e){
		anwserOptionTwoRadioButton.setSelected(false);
		anwserOptionThreeRadioButton.setSelected(false);
	}
	private void alterRadioButtonOneAndThreeSelections(ActionEvent e){
		anwserOptionOneRadioButton.setSelected(false);
		anwserOptionThreeRadioButton.setSelected(false);
	}
	private void alterRadioButtonOneAndTwoSelections(ActionEvent e){
		anwserOptionOneRadioButton.setSelected(false);
		anwserOptionTwoRadioButton.setSelected(false);
	}
		
	private void populateNextSetOfQuestions(ActionEvent e, Stage homeStage, Quiz currentStudentQuiz){
		System.out.println("currentStudentQuiz: " + currentStudentQuiz.getId()); 	
		
		anwserOptionOneRadioButton.setSelected(false);
		anwserOptionTwoRadioButton.setSelected(false);
		anwserOptionThreeRadioButton.setSelected(false);
		
		Quiz myQuiz = currentStudentQuiz;
			ArrayList<Question> currentQuestion = myQuiz.getQuestionBank();
					
			if(currentQuestion == null){
				System.out.println("Is Null");
			}else{
				
			
			
			Question q = currentQuestion.get(questionCounter);
			
			ArrayList<Answer> currentAnwsers = q.getAnswers();
			System.out.println("dhdh");
			
			String question = currentQuestion.get(questionCounter).getQuestion().toString();
		
				anwserOne = currentAnwsers.get(0);
				anwserTwo = currentAnwsers.get(1);
				anwserThree = currentAnwsers.get(2);
				
				
				quizQuestion.setText(question);
			
				anwserOptionOne.setText(anwserOne.getAnswer().toString());
				anwserOptionTwo.setText(anwserTwo.getAnswer().toString());
				anwserOptionThree.setText(anwserThree.getAnswer().toString());
				
				
				//this code is for boolean checking of anwsers
				if(anwserOne.isCorrect()){
					System.out.println("Anwser One Correct");
				}
			
				if(anwserTwo.isCorrect()){
					System.out.println("Anwser Two Correct");
				}
				if(anwserThree.isCorrect()){
					System.out.println("Anwser Three Correct");
				}
		
		boolean questionAnwseredCorrectly = checkAnwserCorrect();
		
		if(questionAnwseredCorrectly == false){
			System.out.println("False");
		}else{
			System.out.println("True");
		}
		
		if(questionAnwseredCorrectly == true){	
			correctAnwserCounter++;
			System.out.println("Correct answer");
			
		}
		
		storePupilsFinalQuizScore(e);
		
		questionCounter++;
		
		if(questionCounter>=10){
			//show submit button
		}
			}
	}
	
	private boolean checkAnwserCorrect(){
		
		if(anwserOptionOneRadioButton.isSelected()){
			
			if(anwserOne.isCorrect() == true){
				return true;
			}else{
				return false;
			}
		}
		if(anwserOptionTwoRadioButton.isSelected()){
			
			if(anwserTwo.isCorrect() == true){
				return true;
			}else{
				return false;
			}
		}
			
		if(anwserOptionThreeRadioButton.isSelected()){
			
			if(anwserTwo.isCorrect() == true){
				return true;
			}else{
				return false;
			}	
		}
		return false;	
	}
	
	
	private void storePupilsFinalQuizScore(ActionEvent e){
		
	}
}

