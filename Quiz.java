package quiz;

import java.util.ArrayList;

public class Quiz {
	
	private String id;
	private QuizType category;
	private ArrayList<Question> questionBank;
	private String date_submitted;
	
	public Quiz(){
		
	}
	
	public Quiz(String id, String date_submitted, QuizType category, ArrayList<Question> questionBank) {
		this.id = id;
		this.date_submitted = date_submitted;
		this.category = category;
		this.questionBank = questionBank;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public QuizType getCategory() {
		return category;
	}
	public void setCategory(QuizType category) {
		this.category = category;
	}
	public ArrayList<Question> getQuestionBank() {
		return questionBank;
	}
	public void setQuestionBank(ArrayList<Question> questionBank) {
		this.questionBank = questionBank;
	}
	
	public String getDate_submitted() {
		return date_submitted;
	}

	public void setDate_submitted(String date_submitted) {
		this.date_submitted = date_submitted;
	}
	
	public void getQuiz(){
		try {
		
		System.out.println(this.id);
		System.out.println(this.date_submitted);
		System.out.println("Category : " + this.category + "\n");
		
		for (int loop = 0; loop < this.questionBank.size(); loop++) {
			System.out.printf("%s %d\n", "Question", (loop+1));
			this.questionBank.get(loop).printQuestion();
			System.out.println();
		}
		} catch (NullPointerException e){
			System.out.println("Quiz Details Missing");
		}
		
	}

	

	
	

}
