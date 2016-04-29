package quiz;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUIEditDeleteQuiz {

	private int correctAnwserCounter = 0, questionCounter = 0;
	public Label quizNameTitle, quizCatTitle, addQuestionTitle, questionCountTitle;

	private RadioButton anwserOptionOneRadioButton, anwserOptionTwoRadioButton, anwserOptionThreeRadioButton;

	public Label quizQuestionLabel, anwserOptionOneLabel, anwserOptionTwoLabel, anwserOptionThreeLabel,
			anwserOptionFourLabel, anwserOptionFiveLabel;

	private Answer anwserOne, anwserTwo, anwserThree;

	public TextField pupilTxtfSearch, quizQuestion, anwserOptionOne, anwserOptionTwo, anwserOptionThree,
			anwserOptionFour, anwserOptionFive;

	public Button createNewQ, selectExistingQ, pupilInsertBtn, pupilBtnSearch;

	public Label pupilLabelAvg, label1, label2, label3;
	public TextField pupilGiveID, pupilPutAvg;
	public Button pupilBtnGetAvg;

	Quiz quiz = new Quiz();

	public GUIEditDeleteQuiz(Stage homeStage, Quiz myQuiz) {
		ActionEvent e = null;
		populateNextSetOfQuestions(e, homeStage, quiz);
		quiz = myQuiz;
		Stage newStage = setScene(homeStage, quiz);
		newStage.show();

	}

	private Stage setScene(Stage homeStage, Quiz quiz) {

		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(homeStage, quiz));

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

		Label title = new Label("View Quiz");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}

	private HBox optionButtons(Stage homeStage, Quiz quiz) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 150));
		hbox.setSpacing(10);

		Button submitBt, cancelBt, editBt, deleteBt;
		submitBt = new Button("Save Changes");
		submitBt.setPrefSize(120, 18);
		cancelBt = new Button("Cancel");
		cancelBt.setPrefSize(120, 18);
		editBt = new Button("Edit Quiz");
		editBt.setPrefSize(120, 18);
		deleteBt = new Button("Delete Quiz");
		deleteBt.setPrefSize(120, 18);

		editBt.setOnAction(e -> setEdit());
		cancelBt.setOnAction(e -> cancel(homeStage));

		hbox.getChildren().addAll(cancelBt, deleteBt, editBt, submitBt);

		return hbox;
	}

	private GridPane display(Stage homeStage) {

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);

		label1 = new Label();
		label2 = new Label();
		label3 = new Label();
		Button  nextQuestion;
		nextQuestion = new Button("Next Question");
		nextQuestion.setPrefSize(100, 18);
		nextQuestion.setOnAction(e -> populateNextSetOfQuestions(e, homeStage, quiz));

		quizQuestionLabel = new Label("Question");
		quizQuestion = new TextField();
		quizQuestion.setPrefWidth(400);
		quizQuestion.setEditable(false);

		anwserOptionOneLabel = new Label("Option 1:");
		anwserOptionOne = new TextField(null);
		anwserOptionOne.setPrefWidth(200);
		anwserOptionOne.setEditable(false);
		anwserOptionOneRadioButton = new RadioButton("Option 1:");

		anwserOptionTwoLabel = new Label("Option 2:");
		anwserOptionTwo = new TextField(null);
		anwserOptionTwo.setEditable(false);
		anwserOptionTwoRadioButton = new RadioButton("Option 2:");

		anwserOptionThreeLabel = new Label("Option 3:");
		anwserOptionThree = new TextField(null);
		anwserOptionThree.setEditable(false);
		anwserOptionThreeRadioButton = new RadioButton("Option 3:");

		grid.add(quizQuestionLabel, 0, 1);
		grid.add(quizQuestion, 1, 1);

		grid.add(anwserOptionOneLabel, 0, 2);
		grid.add(anwserOptionOne, 1, 2);
		grid.add(label1, 2, 2);

		grid.add(anwserOptionTwoLabel, 0, 3);
		grid.add(anwserOptionTwo, 1, 3);
		grid.add(label2, 2, 3);

		grid.add(anwserOptionThreeLabel, 0, 4);
		grid.add(anwserOptionThree, 1, 4);
		grid.add(label3, 2, 4);
		
		grid.add(nextQuestion, 2, 5);

		return grid;

	}

	private void cancel(Stage homeStage) {

		GUIQuizOverview newStage = new GUIQuizOverview(homeStage);
		newStage.getClass();
	}

	private void populateNextSetOfQuestions(ActionEvent e, Stage homeStage, Quiz currentStudentQuiz) {
		System.out.println("currentStudentQuiz: " + currentStudentQuiz.getId());

		Quiz myQuiz = currentStudentQuiz;
		ArrayList<Question> currentQuestion = myQuiz.getQuestionBank();

		if (currentQuestion == null) {
			System.out.println("Is Null");
		} else {

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

			// this code is for boolean checking of anwsers
			if (anwserOne.isCorrect()) {
				System.out.println("Anwser One Correct");
			}

			if (anwserTwo.isCorrect()) {
				System.out.println("Anwser Two Correct");
			}
			if (anwserThree.isCorrect()) {
				System.out.println("Anwser Three Correct");
			}

			setAnwserCorrect();

			questionCounter++;

			if (questionCounter >= 10) {
				// show submit button
			}
		}
	}

	private void setAnwserCorrect() {

		label1.setText("");
		label2.setText("");
		label3.setText("");
		// this code is for boolean checking of anwsers
		if (anwserOne.isCorrect()) {
			label1.setText("Anwser One Correct");
		}

		if (anwserTwo.isCorrect()) {
			label2.setText("Anwser Two Correct");
		}
		if (anwserThree.isCorrect()) {
			label3.setText("Anwser Three Correct");
		}
	}
	
	private void setEdit(){
		quizQuestion.setEditable(true);
		anwserOptionOne.setEditable(true);
		anwserOptionTwo.setEditable(true);
		anwserOptionThree.setEditable(true);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Edit Mode Set");
		alert.setHeaderText("You can now edit quiz");
		alert.showAndWait();
	}

}
