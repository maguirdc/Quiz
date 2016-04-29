package quiz;
/*
 * Class to create score object
 */
public class Score {

	private String quizId;
	private int score;
	private String date;
	private QuizType category;

	//default constructor
	public Score() {

	}

	//constructor with args
	public Score(String quizId, int score, String date, QuizType category) {
		super();
		this.quizId = quizId;
		this.score = score;
		this.date = date;
		this.setCategory(category);
	}

	public String getQuizId() {
		return quizId;
	}

	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public QuizType getCategory() {
		return category;
	}

	public void setCategory(QuizType category) {
		this.category = category;
	}

}
