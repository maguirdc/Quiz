package quiz;

public class IdCreation {
	
	//JDBC QUERY TO FIND LAST NUMBER OF EACH WITHIN DATABASE
	public static int quizNum = 000001;
	public static int quesNum = 00001;
	public static int ansNum = 0001;
	public static int pupilNum = 000001;
	public static int staffNum = 000001;
	public static int guardNum = 000001;

	
	//IF WORKING INSTANTIATE IDCREATION AS SOON AS QUIZ OPENS
	public static String newQuizId() {

		Integer.toString(quizNum);
		String Id = "Quiz" + quizNum;
		quizNum++;
		return Id;
	}

	public static String newQuestionId(QuizType category) {

		Integer.toString(quesNum);
		String Id = "";
		switch (category) {
		case BIOLOGY:
			Id = "BIO" + quesNum;
			break;
		case CHEMISTRY:
			Id = "CHM" + quesNum;
			break;
		case PHYSICS:
			Id = "PHY" + quesNum;
			break;	
		}
		
		quesNum++;
		return Id;
	}
	
	public static String newAnswerId(QuizType category) {

		Integer.toString(ansNum);
		String Id = "";
		switch (category) {
		case BIOLOGY:
			Id = "BIOA" + ansNum;
			break;
		case CHEMISTRY:
			Id = "CHMA" + ansNum;
			break;
		case PHYSICS:
			Id = "PHAN" + ansNum;
			break;	
		}
		
		ansNum++;
		return Id;
	}
	
	public static String newPupilId() {

		Integer.toString(pupilNum);
		String Id = "PU" + pupilNum;
		pupilNum++;
		return Id;
	}
	
	public static String newStaffId() {

		Integer.toString(staffNum);
		String Id = "ST" + staffNum;
		staffNum++;
		return Id;
	}
	
	public static String newGuardianId() {

		Integer.toString(guardNum);
		String Id = "GP" + guardNum;
		guardNum++;
		return Id;
	}

}
