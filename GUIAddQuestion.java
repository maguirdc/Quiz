package quiz;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUIAddQuestion {

	public static ArrayList<Question> newQuestionBank = new ArrayList<>();
	public static Question newQuestion;
	public static Label questionTitle, answerTitle1, answerTitle2, answerTitle3;
	public static TextField question, answer1, answer2, answer3;
	public static CheckBox cb1, cb2, cb3;

	public QuizType cat;
	
	public GUIAddQuestion() {
	}

	public Stage AddQuestion() {

		Stage newStage = new Stage();
		setScene(newStage);

		return newStage;
	}

	private Stage setScene(Stage newStage) {

		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(newStage));

		Scene scene = new Scene(border, BorderSize.HEIGHT.getValue(), BorderSize.WIDTH.getValue());

		border.setCenter(newQuestionVBox());

		newStage.setScene(scene);

		return newStage;

	}

	private HBox title() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15));
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: lightgrey;");

		Label title = new Label("Create New Question");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}

	private HBox optionButtons(Stage newStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 450));
		hbox.setSpacing(10);

		Button submitBt, cancel;
		submitBt = new Button("Save");
		submitBt.setPrefSize(100, 18);
		cancel = new Button("Cancel");
		cancel.setPrefSize(100, 18);

		submitBt.setOnAction(e -> save(newStage));
		cancel.setOnAction(e -> cancel(newStage));

		hbox.getChildren().addAll(cancel, submitBt);

		return hbox;
	}

	private VBox newQuestionVBox() {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(20, 100, 10, 70));
		vbox.setSpacing(10);

		questionTitle = new Label("Enter Question :");
		question = new TextField(null);
		answerTitle1 = new Label("Answer 1 :");
		answer1 = new TextField(null);
		answerTitle2 = new Label("Answer 2 :");
		answer2 = new TextField(null);
		answerTitle3 = new Label("Answer 2 :");
		answer3 = new TextField(null);
		cb1 = new CheckBox("Correct");
		cb2 = new CheckBox("Correct");
		cb3 = new CheckBox("Correct");

		ArrayList<Node> attributes = new ArrayList<>();

		attributes.add(questionTitle);
		attributes.add(question);
		attributes.add(answerTitle1);
		attributes.add(answer1);
		attributes.add(cb1);
		attributes.add(answerTitle2);
		attributes.add(answer2);
		attributes.add(cb2);
		attributes.add(answerTitle3);
		attributes.add(answer3);
		attributes.add(cb3);

		vbox.getChildren().addAll(attributes);

		cb1.setOnAction(e -> toggleOptions(cb2, cb3));
		cb2.setOnAction(e -> toggleOptions(cb1, cb3));
		cb3.setOnAction(e -> toggleOptions(cb1, cb2));

		return vbox;
	}

	private void toggleOptions(CheckBox cb1, CheckBox cb2) {
		if (cb1.isSelected()) {
			cb1.setSelected(false);
		}

		if (cb2.isSelected()) {
			cb2.setSelected(false);
		}

	}

	private Question questionCreation() {

		try {
			newQuestion = new Question();
			newQuestion.setId(IdCreation.newQuestionId(cat));
			newQuestion.setQuestion(question.getText());

			ArrayList<Answer> answers = new ArrayList<>();

			Answer ans1 = new Answer(IdCreation.newAnswerId(cat), answer1.getText(), cb1.isSelected());
			Answer ans2 = new Answer(IdCreation.newAnswerId(cat), answer2.getText(), cb2.isSelected());
			Answer ans3 = new Answer(IdCreation.newAnswerId(cat), answer3.getText(), cb3.isSelected());

			answers.add(ans1);
			answers.add(ans2);
			answers.add(ans3);

			newQuestion.setAnswers(answers);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return newQuestion;
	}

	private void save(Stage newStage) {

		newQuestionBank.add(questionCreation());
		newStage.close();

	}

	private void cancel(Stage newStage) {

		newStage.close();

	}

}
