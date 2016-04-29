package quiz;

import java.util.ArrayList;


public class Question {

	private String id;
	private String question;
	private ArrayList<Answer> answers;
	
	public Question(){
		
	}

	public Question(String id, String question, ArrayList<Answer> answers) {
		this.id = id;
		this.question = question;
		this.answers = answers;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public void printQuestion() {
		System.out.println(this.id + " : "+this.question);

		for (int loop = 0; loop < answers.size(); loop++) {
			System.out.println(loop + 1 + " : " + this.answers.get(loop).getAnswer() + " " + this.answers.get(loop).getId());
		}

	}

}
