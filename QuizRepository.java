package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuizRepository {	
	Connection con;
	
	public QuizRepository()
	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSL=false", "root", "Pa$$w0rd");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Quiz> retrieveAllQuizzes()
	{
		// Get all of the quizzes from the database
		System.out.println("bug");
		ArrayList<Quiz> quizzes = getAllQuizzes();
		
		// Add the questions to the correct quiz
		for(Quiz quiz : quizzes)
		{
			ArrayList<Question> questions = getAllQuizQuestions(quiz);
			
			for(Question question : questions)
			{
				ArrayList<Answer> answers = getAllAnswersToQuestion(question);
				question.setAnswers(answers);
			}
			
			quiz.setQuestionBank(questions);
		}
		
		return quizzes;
	}
	
	public ArrayList<Quiz> retrieveQuzziesByType(QuizType type)
	{
		ArrayList<Quiz> quizzes = retrieveAllQuizzes();
		ArrayList<Quiz> quizOfType = new ArrayList<Quiz>();
		
		for(int i = 0; i < quizzes.size(); i++)
		{
			if (quizzes.get(i).getCategory() == type)
			{
				quizOfType.add(quizzes.get(i));
			}
		}
				
		return quizOfType;
	}

	public ArrayList<Quiz> getAllQuizzes()
	{
		ArrayList<Quiz> retrievedQuizzes = new ArrayList<Quiz>();
		
		// query
		String query = "SELECT quiz_id, date_submitted, staff_id, category "
				+ "FROM quiz_by_category";
		
		//String query = "SELECT * "
			//			+ "FROM quiz_by_category";
		
		try {

			Statement stmt = con.createStatement();
		
			ResultSet rs = stmt.executeQuery(query);
		
			
			while (rs.next()) {
				Quiz quiz = new Quiz();
				
				//System.out.println("Bug");
				String id = rs.getString("quiz_id");
				quiz.setId(id);
				
				String category = rs.getString("category");
				quiz.setCategory(convertQuizCategoryToQuizType(category));
				
				String date_submitted = rs.getString("date_submitted");
				quiz.setDate_submitted(date_submitted);
				
				
				retrievedQuizzes.add(quiz);
			}
			
			return retrievedQuizzes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private ArrayList<Question> getAllQuizQuestions(Quiz quiz)
	{		
		// SELECT id, question FROM questions WHERE quiz_id = 'QZ000001';
		String query = String.format("SELECT id, question "
				+ " FROM questions "
				+ " WHERE quiz_id = '%s'; ", quiz.getId());
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			ArrayList<Question> questions = new ArrayList<Question>();
			
			while (rs.next()) {
				Question question = new Question();
				
				String id = rs.getString("id");
				question.setId(id);
				
				String text = rs.getString("question");
				question.setQuestion(text);;

				questions.add(question);
			}
			
			return questions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private ArrayList<Answer> getAllAnswersToQuestion(Question question)
	{
		// query
				String query = String.format("SELECT id, answer, correct_answer "
						+ "FROM answers "
						+ "WHERE question_id = '%s';", question.getId());
				
				try {
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery(query);
					
					ArrayList<Answer> answers = new ArrayList<Answer>();
					
					while (rs.next()) {
						Answer answer = new Answer();
						
						String id = rs.getString("id");
						answer.setId(id);
						
						String text = rs.getString("answer");
						answer.setAnswer(text);
						
						boolean correct = rs.getBoolean("correct_answer");
						answer.setCorrect(correct);

						answers.add(answer);
					}
					
					return answers;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return null;
	}
	
	public int getQuizCount()
	{
		String query = "SELECT COUNT(*) as count FROM quiz";
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public void addQuiz(Quiz quiz)
	{
		String query = String.format("INSERT INTO quiz (quiz_id, date_submitted)"
				+ " VALUES ('%s', CURDATE())", quiz.getId());
		
		try {
			Statement stmt = con.createStatement();
			
			int rowsAffected = stmt.executeUpdate(query);
			
			for (Question question : quiz.getQuestionBank())
			{
				switch (quiz.getCategory())
				{
					case BIOLOGY:
						addQuestion(question, quiz.getId(), "biology");
						break;
					case CHEMISTRY:
						addQuestion(question, quiz.getId(), "chemistry");
						break;
					case PHYSICS:
						addQuestion(question, quiz.getId(), "physics");
						break;
					default:
						return;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addQuestion(Question question, String quizId, String table)
	{
		String query = String.format("INSERT INTO %s_questions (%s_id, question, correct_answer, quiz_id)"
				+ " VALUES ('%s', '%s','%s','%s');", table, table, question.getId(), question.getQuestion(), "", quizId);
		
		try {
			Statement stmt = con.createStatement();
			
			int rowsAffected = stmt.executeUpdate(query);
			
			for (Answer answer : question.getAnswers())
			{
				addAnswer(answer, question.getId(), table);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addAnswer(Answer answer, String questionId, String table)
	{
		String query = String.format("INSERT INTO %s_answers (%s_answers_id, answers, correct_answer, biology_id) "
				+ "VALUES ('%s', '%s', %s, '%s');", table, table, answer.getId(), answer.getAnswer(), answer.isCorrect(), questionId);
		
		try {
			Statement stmt = con.createStatement();
			
			int rowsAffected = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private QuizType convertQuizCategoryToQuizType(String type)
	{
		if (type.toLowerCase().equals("chemistry")) return QuizType.CHEMISTRY;
		if (type.toLowerCase().equals("biology")) return QuizType.BIOLOGY;
		//if (type.toLowerCase().equals("physics")) 
			return QuizType.PHYSICS;
	}	

}
