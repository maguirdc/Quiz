package quiz;
import java.util.ArrayList;
import java.util.UUID;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateQuiz {

	VBox createQuizPage;
	Button quizAnwser1,quizAnwser2,quizAnwser3,quizAnwser4,quizAnwser5,
	quizAnwser6,quizAnwser7,quizAnwser8,quizAnwser9,quizAnwser10;
	TextField quizPageQuestion1, quizPageQuestion2, quizPageQuestion3, quizPageQuestion4, 
	quizPageQuestion5,quizPageQuestion6, quizPageQuestion7, 
	quizPageQuestion8, quizPageQuestion9, quizPageQuestion10;
	Label quizPagequestion1Label, quizPageQuestion2Label, quizPageQuestion3Label, quizPageQuestion4Label, 
	quizPageQuestion5Label,quizPageQuestion6Label, quizPageQuestion7Label, 
	quizPageQuestion8Label, quizPageQuestion9Label, quizPageQuestion10Label;

	public CreateQuiz(Stage HomeStage) {
		createControls(HomeStage);
		HomeStage.show();
	}
	
	private void createControls(Stage HomeStage){
		createQuizPage = new VBox(10);
		createQuizPage.setAlignment(Pos.CENTER);
		quizAnwser1 = new Button("Anwser 1:");
		quizAnwser2 = new Button("Anwser 2:");
		quizAnwser3 = new Button("Anwser 3:");
		quizAnwser4 = new Button("Anwser 4:");
		quizAnwser5 = new Button("Anwser 5:");
		quizAnwser6 = new Button("Anwser 6:");
		quizAnwser7 = new Button("Anwser 7:");
		quizAnwser8 = new Button("Anwser 8:");
		quizAnwser9 = new Button("Anwser 9:");
		quizAnwser10 = new Button("Anwser 10:");
		quizPageQuestion1 = new TextField();
		quizPageQuestion2 = new TextField();
		quizPageQuestion3 = new TextField();
		quizPageQuestion4 = new TextField();
		quizPageQuestion5 = new TextField();
		quizPageQuestion1.setMaxWidth(200);
		quizPageQuestion2.setMaxWidth(200);
		quizPageQuestion3.setMaxWidth(200);
		quizPageQuestion4.setMaxWidth(200);
		quizPageQuestion5.setMaxWidth(200);
		quizPagequestion1Label = new Label("Question 1:");
		quizPageQuestion2Label = new Label("Question 2:");
		quizPageQuestion3Label = new Label("Question 3:");
		quizPageQuestion4Label = new Label("Question 4:");
		quizPageQuestion5Label = new Label("Question 5:");
		createQuizPage.getChildren().addAll(quizPagequestion1Label, quizPageQuestion1,quizPageQuestion2Label, quizPageQuestion2,quizPageQuestion3Label, quizPageQuestion3,quizPageQuestion4Label, quizPageQuestion4,quizPageQuestion5Label, quizPageQuestion5,
				quizAnwser1,quizAnwser2,quizAnwser3,quizAnwser4,quizAnwser5);
		HomeStage.setScene(new Scene(createQuizPage, 700, 700));
		HomeStage.setTitle("Quiz");
	}
	
	private void Test()
	{
		QuizRepository repository = new QuizRepository();

		Quiz quiz = new Quiz();
		int count = repository.getQuizCount();
		String quizId = "QZ" + count;
		quiz.setId(quizId);
		
		// set properties for the quiz -- examples:
		// work out which checkbox was selected
		quiz.setCategory(QuizType.BIOLOGY);
		
		// Once the quiz category is created, add some questions to it
		// quiz.setQuestionBank(questionBank);
		// when finished building
		
		repository.addQuiz(quiz);
	}
	
	// On question screen - create array list to store questions
	
	private void CreateQuestion()
	{
		Question question = new Question();
		
		String quesitonId = UUID.randomUUID().toString(); // trim to last 8 characters
		question.setId(quesitonId); // set the questionId
		question.setQuestion(""); //whatever text from the text box
		
		// get answers from the other text boxes
		ArrayList<Answer> answers = new ArrayList<Answer>();
		
		// repeat this for all 3 answers
		Answer answer1 = new Answer();
		answer1.setId("");
		answer1.setCorrect(true); // read from checkbox
		answer1.setAnswer(""); // read from text box
		
		// Add all of the answers to the arraylist
		answers.add(answer1);
		// answers.add(answer2); /// and 3
		
		question.setAnswers(answers);
		
		// Home screen arrayList<Question>()
		/// Add to question bank
		
		// clear all fields
		
	}
	
	private void SaveButton()
	{
		Quiz quiz = new Quiz(); // this is actually the quiz we passed into the question/answer screen
		
		// quiz.setQuestionBank();
		
		QuizRepository repository = new QuizRepository();
		repository.addQuiz(quiz);
	}
}
