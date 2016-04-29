package quiz;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUINewQuiz {

	public Quiz newQuiz;
	public Label quizNameTitle, quizCatTitle, addQuestionTitle, questionCountTitle, questionCount;
	public TextField quizName;
	public CheckBox cb1, cb2, cb3;
	public Button createNewQ, selectExistingQ;

	public GUINewQuiz(Stage homeStage) {

		Stage newStage = setScene(homeStage);
		newStage.show();

	}

	private Stage setScene(Stage homeStage) {

		int questionCount = 0;

		homeStage.setTitle("New Quiz");

		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(homeStage));

		Scene scene = new Scene(border, BorderSize.HEIGHT.getValue(), BorderSize.WIDTH.getValue());

		border.setCenter(display(questionCount, homeStage));

		homeStage.setScene(scene);

		return homeStage;

	}

	private HBox title() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15));
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: lightgrey;");

		Label title = new Label("Create New Quiz");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}

	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 450));
		hbox.setSpacing(10);

		Button preview, submitBt, cancel;
		preview = new Button("Preview Quiz");
		preview.setPrefSize(100, 18);
		submitBt = new Button("Save Quiz");
		submitBt.setPrefSize(100, 18);
		cancel = new Button("Cancel");
		cancel.setPrefSize(100, 18);

		hbox.getChildren().addAll(preview, cancel, submitBt);

		submitBt.setOnAction(e -> save(homeStage));
		cancel.setOnAction(e -> cancel(homeStage));

		return hbox;
	}

	private GridPane display(int num, Stage homeStage) {

		String questionNum = Integer.toString(num);

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);

		quizNameTitle = new Label("Quiz ID: ");
		quizName = new TextField("QZ000004");
		quizCatTitle = new Label("Please select category: ");
		questionCountTitle = new Label("Current number of questions: ");
		questionCount = new Label(questionNum);
		addQuestionTitle = new Label("Add Question :");
		createNewQ = new Button("Create New Question");
		createNewQ.setPrefSize(170, 18);
		createNewQ.setOnAction(e -> newStage(e));

		selectExistingQ = new Button("Select Existing Question");
		selectExistingQ.setPrefSize(170, 15);

		selectExistingQ.setOnAction(e -> selectQuestion(homeStage));

		cb1 = new CheckBox(QuizType.BIOLOGY.name());
		cb2 = new CheckBox(QuizType.CHEMISTRY.name());
		cb3 = new CheckBox(QuizType.PHYSICS.name());

		cb1.setOnAction(e -> toggleOptions(cb2, cb3));
		cb2.setOnAction(e -> toggleOptions(cb1, cb3));
		cb3.setOnAction(e -> toggleOptions(cb1, cb2));

		grid.add(quizNameTitle, 0, 0);
		grid.add(quizName, 0, 1);
		grid.add(quizCatTitle, 1, 0);
		grid.add(cb1, 1, 1);
		grid.add(cb2, 1, 2);
		grid.add(cb3, 1, 3);
		grid.add(addQuestionTitle, 0, 6);
		grid.add(selectExistingQ, 0, 7);
		grid.add(createNewQ, 0, 8);
		grid.add(questionCountTitle, 1, 6);
		grid.add(questionCount, 1, 7);

		return grid;
	}

	private void toggleOptions(CheckBox cb1, CheckBox cb2) {
		if (cb1.isSelected()) {
			cb1.setSelected(false);
		}

		if (cb2.isSelected()) {
			cb2.setSelected(false);
		}

	}

	private void selectQuestion(Stage homeStage) {
		GUISelectQuestion newStage = new GUISelectQuestion(homeStage);
		newStage.getClass();
	}

	private void newStage(ActionEvent e) {

		QuizType cat;

		if (cb1.isSelected()) {
			cat = QuizType.BIOLOGY;
		} else if (cb2.isSelected()) {
			cat = QuizType.CHEMISTRY;
		} else {
			cat = QuizType.PHYSICS;
		}

		GUIAddQuestion newGUI = new GUIAddQuestion();
		newGUI.cat = cat;

		Stage newStage = newGUI.AddQuestion();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.showAndWait();

	}

	private void quizCreation() {

		newQuiz = new Quiz();

		QuizType cat;

		if (cb1.isSelected()) {
			cat = QuizType.BIOLOGY;
		} else if (cb2.isSelected()) {
			cat = QuizType.CHEMISTRY;
		} else {
			cat = QuizType.PHYSICS;
		}

		try {

			newQuiz.setId(quizName.getText());
			newQuiz.setCategory(cat);

			newQuiz.setQuestionBank(GUIAddQuestion.newQuestionBank);

			newQuiz.getQuiz();

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	private void save(Stage homeStage) {

		quizCreation();
		// JDBC GOES HERE
		//Send the newly created quiz to the database
		GUIQuizOverview newStage = new GUIQuizOverview(homeStage);
		newStage.getClass();

		GUIAddQuestion.newQuestionBank.clear();

	}

	private void cancel(Stage homeStage) {

		GUIQuizOverview newStage = new GUIQuizOverview(homeStage);
		newStage.getClass();

		GUIAddQuestion.newQuestionBank.clear();
		
		//remove if we get idCreation working
		IdCreation.quesNum = 1001;
		IdCreation.ansNum = 1001;
	}

}
