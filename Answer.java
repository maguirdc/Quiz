package quiz;

public class Answer {
	
	private String id;
	private String answer;
	private boolean correct;

	public Answer() {		
	}

	public Answer(String id, String answer, boolean correct) {
		this.id = id;
		this.answer = answer;
		this.correct = correct;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

}
