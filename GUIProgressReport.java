package quiz;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUIProgressReport {

	Label pupilFirstName, pupilLastName, pupilId, pupilEmail, gFirstName, gLastName, gEmail, gMobile;
	Text pupilFirstNameText, pupilLastNameText,  pupilIdText, pupilEmailText, gFirstNameText, gLastNameText, gEmailText, gMobileText, sendEmailText;
	Pupil pupil;
	Button send, cancel;
	TableView<Score> scores;
	
	SqlReport sqlReport = new SqlReport();
	ArrayList <Score> scoreList = new ArrayList<>();
	ArrayList <Profile> profileList = new ArrayList<>();
	Score score = new Score();
	Profile profile = new Profile();
	
	
	//public static String pupil_report_id;

	public GUIProgressReport(Stage homeStage) {

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

		Label title = new Label("Pupil Progress Report");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}

	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 180));
		hbox.setSpacing(10);

		cancel = new Button("Cancel");
		cancel.setPrefSize(100, 18);
		send = new Button("Send Email");
		send.setPrefSize(200, 18);
		sendEmailText = new Text("Send Email of Report to Guardian:");

		hbox.getChildren().addAll(sendEmailText, send, cancel);

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
		
		String id = GUIPupilOverview.pupil_report_id;
		
		scoreList = sqlReport.searchReport(id);
		
		profile = sqlReport.searchProfile(id);
	
		ObservableList<Score> data = FXCollections.observableArrayList(scoreList);
		
		
		pupilId = new Label("Pupil ID: ");
		pupilFirstName = new Label("Pupil First Name: ");
		pupilLastName = new Label("Pupil Last Name: ");
		pupilEmail = new Label("Pupil Email: ");
		gFirstName = new Label("Guardian First Name: ");
		gLastName = new Label("Guardian Last Name: ");
		gEmail = new Label("Gaurdian Email: ");
		gMobile = new Label("Guardian Mobile: ");
		
		pupilIdText = new Text(id);
		pupilFirstNameText = new Text(profile.getPupilFirstName());
		pupilLastNameText = new Text(profile.getPupilLastName());
		pupilEmailText = new Text(profile.getPupilEmail());
		
		gFirstNameText = new Text(profile.getFirstName());
		gLastNameText = new Text(profile.getLastName());
		gEmailText = new Text(profile.getEmail());
		gMobileText = new Text(profile.getMobileNumber());

		scores = new TableView<Score>();
		scores.setEditable(true);
		scores.setPrefSize(500, 500);
		scores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<Score, String> quizIdCol = new TableColumn<Score, String>("Quiz ID");
		quizIdCol.setMinWidth(90);
		quizIdCol.setCellValueFactory(new PropertyValueFactory<Score, String>("quizId"));

		TableColumn<Score, String> scoreCol = new TableColumn<Score, String>("Score");
		scoreCol.setMinWidth(90);
		scoreCol.setCellValueFactory(new PropertyValueFactory<Score, String>("score"));

		TableColumn<Score, String> subjectCol = new TableColumn<Score, String>("Subject");
		subjectCol.setMinWidth(90);
		subjectCol.setCellValueFactory(new PropertyValueFactory<Score, String>("category"));

		TableColumn<Score, String> dateCol = new TableColumn<Score, String>("Date");
		dateCol.setMinWidth(90);
		dateCol.setCellValueFactory(new PropertyValueFactory<Score, String>("date"));

		scores.setItems(data);
		scores.getColumns().addAll(quizIdCol, scoreCol, subjectCol, dateCol);
		

		grid.add(pupilId, 0, 0, 1, 1);
		grid.add(pupilIdText, 1, 0, 1, 1);
		grid.add(pupilFirstName, 0, 1, 1, 1);
		grid.add(pupilFirstNameText, 1, 1, 1, 1);
		grid.add(pupilLastName, 0, 2, 1, 1);
		grid.add(pupilLastNameText, 1, 2, 1, 1);
		grid.add(pupilEmail, 0, 3, 1, 1);
		grid.add(pupilEmailText, 1, 3, 1, 1);
		grid.add(gFirstName, 3, 0, 1, 1);
		grid.add(gFirstNameText, 4, 0, 1, 1);
		grid.add(gLastName, 3, 1, 1, 1);
		grid.add(gLastNameText, 4, 1, 1, 1);
		grid.add(gEmail, 3, 2, 1, 1);
		grid.add(gEmailText, 4, 2, 1, 1);
		grid.add(gMobile, 3, 3, 1, 1);
		grid.add(gMobileText, 4, 3, 1, 1);
		grid.add(scores, 0, 4, 5, 5);
		
		send.setOnAction(e -> sendmail(profile.getEmail(), profile.getPupilFirstName(), profile.getFirstName(), profile.getLastName(), scoreList));

		return grid;
	}

	private void cancel(Stage homeStage) {

		GUIPupilOverview newStage = new GUIPupilOverview(homeStage);
		newStage.getClass();
	}

	public void sendmail(String gEmail, String pFirstName, String gFirstName, String gLastName, ArrayList<Score> scoreList)
	{
		String header;
		String body = "";
		String progressReport = "";
		
		header = "QUIZ ID\t\t\tSCORE\t\t\tSUBJECT\t\t\tDATE TAKEN\n\n";
		
		for (int loop = 0; loop < scoreList.size(); loop++){
			body = scoreList.get(loop).getQuizId().toString()+ "\t\t\t" + 
		scoreList.get(loop).getScore()+ "\t\t" + scoreList.get(loop).getCategory().toString()+ "\t\t\t\t" + 
		scoreList.get(loop).getDate().toString() + "\n";
			progressReport += body;
			
		}
		progressReport = header + progressReport;
		
		
		SendEmail send = new SendEmail(gEmail, pFirstName, gFirstName, gLastName, progressReport);
		send.send();
	}

}
