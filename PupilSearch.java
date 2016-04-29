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

public class PupilSearch {
	
	public Label pupilInsert,pupilLabelID,pupilLabelName;
	public TextField pupilInsertID,pupilInsertName, pupilTxtfSearch;
	
	public Label pupilSearchLabel1,pupilSearchLabel2,pupilSearchLabel3,pupilSearchLabel4,
	pupilSearchLabel5,pupilSearchLabel6;
	public TextField pupilSearchField1,pupilSearchField2,pupilSearchField3,pupilSearchField4,
	pupilSearchField5,pupilSearchField6;
	
	public Label pupilLabelSearch;
	public Button createNewQ, selectExistingQ, pupilInsertBtn,pupilBtnSearch;
	
	public PupilSearch(Stage homeStage) {

		Stage newStage = setScene(homeStage);
		newStage.show();

	}

	private Stage setScene(Stage homeStage) {

		int questionCount = 0;

		homeStage.setTitle("Pupil Search");

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

		Label title = new Label("Insert/Search Pupils");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		hbox.getChildren().addAll(title);

		return hbox;
	}

	private HBox optionButtons(Stage homeStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 20, 25, 450));
		hbox.setSpacing(10);

		Button back;
	
		back = new Button("Back");
		back.setPrefSize(100, 18);
		back.setOnAction(e -> back(homeStage));
		
		hbox.getChildren().addAll(back);

		return hbox;
	}

	private GridPane display(int num, Stage homeStage) {

		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 20, 20));
		grid.setAlignment(Pos.CENTER);
		
		pupilLabelSearch = new Label("Please search for a pupil by typing in the Pupil's ID: ");
		pupilTxtfSearch = new TextField(null);
		pupilBtnSearch = new Button("Search");
		
		pupilSearchLabel1 = new Label("Pupil ID");
		pupilSearchLabel2 = new Label("First Name");
		pupilSearchLabel3 = new Label("Last Name");
		pupilSearchLabel4 = new Label("Class ID");
		pupilSearchLabel5 = new Label("Guardian ID");
		pupilSearchLabel6 = new Label("Email");
		
		pupilSearchField1 = new TextField(null);
		pupilSearchField2 = new TextField(null);
		pupilSearchField3 = new TextField(null);
		pupilSearchField4 = new TextField(null);
		pupilSearchField5 = new TextField(null);
		pupilSearchField6 = new TextField(null);
		
		//search
		grid.add(pupilLabelSearch, 0, 0);
		grid.add(pupilTxtfSearch , 1, 0);
		grid.add(pupilBtnSearch  , 2, 0);
		
		grid.add(pupilSearchLabel1,0,1);
		grid.add(pupilSearchField1,1,1);
		
		grid.add(pupilSearchLabel2,0,2);
		grid.add(pupilSearchField2,1,2);
		
		grid.add(pupilSearchLabel3,0,3);
		grid.add(pupilSearchField3,1,3);
		
		grid.add(pupilSearchLabel4,0,4);
		grid.add(pupilSearchField4,1,4);
		
		grid.add(pupilSearchLabel5,0,5);
		grid.add(pupilSearchField5,1,5);
		
		grid.add(pupilSearchLabel6,0,6);
		grid.add(pupilSearchField6,1,6);
		
		pupilBtnSearch.setOnAction(e -> searchSQL());
		
		return grid;
	}
	
	private void searchSQL() {
		
		try {
			Connection con;
			Statement stmt;
		
			//USER YOU WILL NEED TO CHANGE getConnection() FOR YOUR SQL WORKBENCH
			//con = DriverManager.getConnection(your url, "your username", "your password");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema01","root" ,"laggingsql34");        	
			stmt = con.createStatement();

			String search_id = pupilTxtfSearch.getText();
			
			String sql_string = "SELECT * "
					+ "FROM pupils "
					+ "WHERE pupil_id = " + "'"+ search_id + "'";
			System.out.println(sql_string);
			
			
			ResultSet rs = stmt.executeQuery(sql_string);
			
			 while(rs.next()){
				 
				 System.out.println("Returned:" + rs.getString("pupil_id"));
				 System.out.println("Returned:" + rs.getString("first_name"));
				 System.out.println("Returned:" + rs.getString("last_name"));
				 System.out.println("Returned:" + rs.getString("class_id"));
				 System.out.println("Returned:" + rs.getString("guardian_id"));
				 System.out.println("Returned:" + rs.getString("email"));
				 
				 
				 pupilSearchField1.setText(rs.getString("pupil_id"));
				 pupilSearchField2.setText(rs.getString("first_name"));
				 pupilSearchField3.setText(rs.getString("last_name"));
				 pupilSearchField4.setText(rs.getString("class_id"));
				 pupilSearchField5.setText(rs.getString("guardian_id"));
				 pupilSearchField6.setText(rs.getString("email"));
			 }

		} catch(SQLException ex){
			System.err.println("SQLException: " + ex.getMessage());
		}
		
	
	//END OF JDBC RELATED CONTENT----------------------------------------------------------------
	//-------------------------------------------------------------------------------------------
		
	}
	
	
	private void back(Stage homeStage) {

		GUIPupilOverview enrollPupil = new GUIPupilOverview(homeStage);
		enrollPupil.getClass();
	}

}
