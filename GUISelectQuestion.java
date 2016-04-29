package quiz;

import java.util.ArrayList;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GUISelectQuestion {

	public GUISelectQuestion(Stage homeStage) {

		Stage newStage = setScene(homeStage);
		newStage.show();

	}

	private Stage setScene(Stage homeStage) {

		BorderPane border = new BorderPane();
		border.setTop(title());
		border.setBottom(optionButtons(homeStage));

		Scene scene = new Scene(border, BorderSize.HEIGHT.getValue(), BorderSize.WIDTH.getValue());

		border.setCenter(display());

		homeStage.setScene(scene);

		return homeStage;

	}

	private HBox title() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15));
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: lightgrey;");

		Label title = new Label("Select Question");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}

	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 25));
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);

		Button submitBt, cancelBt;
		submitBt = new Button("Add Question");
		submitBt.setPrefSize(120, 18);
		cancelBt = new Button("Cancel");
		cancelBt.setPrefSize(120, 18);

		cancelBt.setOnAction(e -> cancel(homeStage));

		hbox.getChildren().addAll(cancelBt, submitBt);

		return hbox;
	}

	@SuppressWarnings("unchecked")
	private GridPane display() {

		GridPane grid = new GridPane();
		grid.setHgap(30);
		grid.setVgap(20);
		grid.setPadding(new Insets(20, 10, 20, 10));
		grid.setAlignment(Pos.CENTER);

		String id1 = "q1001";
		String id2 = "q1002";

		Answer answer1 = new Answer("a1001","answer 1", true);
		Answer answer2 = new Answer("a1002","answer 2", true);
		Answer answer3 = new Answer("a1003","answer 3", true);

		ArrayList<Answer> answers1 = new ArrayList<>();
		answers1.add(answer1);
		answers1.add(answer2);
		answers1.add(answer3);

		ObservableList<Question> data = FXCollections.observableArrayList(new Question(id1, "Question 1", answers1),
				(new Question(id2, "Question 2", answers1)));

		TableView<Question> qTable = new TableView<Question>();
		qTable.setPrefSize(500, 500);
		qTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Question, String> idCol = new TableColumn<Question, String>("Question ID");
		idCol.setMinWidth(90);
		idCol.setCellValueFactory(new PropertyValueFactory<Question, String>("id"));

		TableColumn<Question, String> questionCol = new TableColumn<Question, String>("Question");
		questionCol.setMinWidth(410);
		questionCol.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));

		TableView<Answer> aTable = new TableView<Answer>();
		aTable.setPrefSize(450, 380);
		aTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		

		ObservableList<Answer> data2 = FXCollections.observableArrayList(new Answer("a1001", "answer 1", true),
				(new Answer("a1002","answer 2", false)), (new Answer("a1003","answer 3", false)));

		TableColumn<Answer, String> answerCol = new TableColumn<Answer, String>("Answer");
		answerCol.setMinWidth(350);
		answerCol.setCellValueFactory(new PropertyValueFactory<Answer, String>("answer"));

		TableColumn<Answer, String> correctCol = new TableColumn<Answer, String>("Correct");
		correctCol.setMinWidth(60);
		correctCol.setCellValueFactory(new PropertyValueFactory<Answer, String>("correct"));

		qTable.setItems(data);
		aTable.setItems(data2);

		qTable.getColumns().addAll(idCol, questionCol);
		aTable.getColumns().addAll(answerCol, correctCol);

		grid.add(qTable, 0, 0, 5, 1);
		grid.add(aTable, 2, 1, 1, 1);
		//grid.setGridLinesVisible(true);

		return grid;

	}

	private void cancel(Stage homeStage) {

		GUINewQuiz newStage = new GUINewQuiz(homeStage);
		newStage.getClass();
	}

}
