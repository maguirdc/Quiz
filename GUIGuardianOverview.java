package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class GUIGuardianOverview {
	
	//public Quiz newQuiz;
	public Label questionCount;
	
	public Label guardianIntro;
	public Label labelInsert1,labelInsert2,labelInsert3,labelInsert4,labelInsert5,labelInsert6;
	
	public TextField txtfInsert1,txtfInsert2,txtfInsert3,txtfInsert4,txtfInsert5,txtfInsert6;
	
	public Button guardianBtnInsert,createNewQ, selectExistingQ, pupilInsertBtn,pupilBtnSearch;
	
	public GUIGuardianOverview(Stage homeStage) {

		Stage newStage = setScene(homeStage);
		newStage.show();

	}

	private Stage setScene(Stage homeStage) {

		int questionCount = 0;

		homeStage.setTitle("Guardian Overview");

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

		Label title = new Label("Insert/Search Guardians");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}

	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 450));
		hbox.setSpacing(10);

		Button search, back;
	
		back = new Button("Back");
		back.setPrefSize(100, 18);
		back.setOnAction(e -> back(homeStage));
		
		search = new Button("Search Guardians");
		search.setPrefSize(130, 18);
		search.setOnAction(e -> createGuardianSearch(homeStage));
		
		hbox.getChildren().addAll(back,search);

		return hbox;
	}

	private GridPane display(int num, Stage homeStage) {

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);
		
		guardianIntro = new Label("Please search for a pupil by typing in the Pupil's ID: ");
		labelInsert1 = new Label("Guardian ID");
		labelInsert2 = new Label("First Name");
		labelInsert3 = new Label("Last Name");
		labelInsert4 = new Label("Email");
		labelInsert5 = new Label("Mobile Number");
		
		txtfInsert1 = new TextField(null);
		txtfInsert2 = new TextField(null);
		txtfInsert3 = new TextField(null);
		txtfInsert4 = new TextField(null);
		txtfInsert5 = new TextField(null);
		
		guardianBtnInsert = new Button("Insert Entity");
		
		//search
		grid.add(labelInsert1, 0, 0);
		grid.add(txtfInsert1 , 1, 0);
		grid.add(guardianBtnInsert, 2, 0);
		
		grid.add(labelInsert2,0,1);
		grid.add(txtfInsert2,1,1);
		
		grid.add(labelInsert3,0,2);
		grid.add(txtfInsert3,1,2);
		
		grid.add(labelInsert4,0,3);
		grid.add(txtfInsert4,1,3);
		
		grid.add(labelInsert5,0,4);
		grid.add(txtfInsert5,1,4);
		
		guardianBtnInsert.setOnAction(e -> insertSQL());
		
		return grid;
	}
	
	private void insertSQL() {
		
		String subString01 = txtfInsert1.getText();
		String subString02 = txtfInsert2.getText();
		String subString03 = txtfInsert3.getText();
		String subString04 = txtfInsert4.getText();
		String subString05 = txtfInsert5.getText();
		
		String string01 = "insert into Guardians values(" + 
				"'" + subString01 + "'," +
				"'" + subString02 + "'," +
				"'" + subString03 + "'," +
				"'" + subString04 + "'," +
				"'" + subString05 + "');";
		
		//-------------------------------------------------------------------------------------------------
		//JDBC RELATED CONTENT------------------------------------------------------------------------------
		//Create a new JDBC connection
		Connection con;
		Statement stmt;

		try {
			
			//USER YOU WILL NEED TO CHANGE getConnection() FOR YOUR SQL WORKBENCH
			//con = DriverManager.getConnection(your url, "your username", "your password");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema01","root" ,"laggingsql34");        	
			stmt = con.createStatement();
			
	        //1)ADD DATA TO THE TABLE WITH A NEW ROW
	        System.out.println(string01);
			stmt.executeUpdate(string01);
	        
			
			System.out.println("The program has executed");
			stmt.close();
			con.close();
			
			} catch(SQLException ex){
				System.err.println("SQLException: " + ex.getMessage());
			}
		
		//END OF JDBC RELATED CONTENT----------------------------------------------------------------
		//-------------------------------------------------------------------------------------------
			
	}
	
	private void createGuardianSearch(Stage homeStage) {
/*
		GUIGuardianSearch newGuardianSearch = new GUIGuardianSearch(homeStage);
		newGuardianSearch.getClass();
	*/	
	}
	
	private void back(Stage homeStage) {
		GUIPupilOverview newStage = new GUIPupilOverview(homeStage);
		newStage.getClass();
		/*
		GUIAddQuestion.newQuestionBank.clear();
		IdCreation.quesNum = 1001;
		IdCreation.ansNum = 1001;
		*/
	}
}





	

