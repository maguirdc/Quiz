package quiz;
import java.util.Properties;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/*
 * Class to send an Email from server to user defined email address
 */
public class SendEmail {
	private final String SenderEmail = "testschoolquiz@gmail.com";
	private final String SenderName = "Test School";
	private final String SenderPassword = "testschoolquiz01";
	private String ReceiverEmail;
	private String pupilName;
	private String teacherFirstName;
	private String teacherLastName;
	private final String Subject = "[SchoolName] Pupil Progress Report";
	private final String MessageHeader = "Hello,\n\tThis is an email from [SchoolName] containing a progress report for ";
	private final String MessageHeader2 = " in regards to their revision for Key Stage 3 Science.\n\n";
	private String progressReport;
	private final String MessageFooter = "\n\nWe believe that it is in your child's best interests that you, their guardian, "
			+ "are kept up to date with their progress in class. However, if you wish to stop receiving these emails please contact "
			+ "me directly.\n\nKind Regards,\n";
	Alert alert;
	public SendEmail() {
	}
	public SendEmail(String receiverEmail, String pupilName, String teacherFirstName, String teacherLastName, String progressReport) {
		super();
		this.ReceiverEmail = receiverEmail;
		this.pupilName = pupilName;
		this.teacherFirstName = teacherFirstName;
		this.teacherLastName = teacherLastName;
		this.progressReport = progressReport;
	}
	public String getSenderEmail() {
		return SenderEmail;
	}
	public String getReceiverEmail() {
		return ReceiverEmail;
	}
	public void setReceiverEmail(String receiverEmail) {
		ReceiverEmail = receiverEmail;
	}
	public String getSubject() {
		return Subject;
	}
	public String getPupilName() {
		return pupilName;
	}
	public void setPupilName(String pupilName) {
		this.pupilName = pupilName;
	}
	
	public String getMessageHeader() {
		return MessageHeader;
	}
	public String getProgressReport() {
		return progressReport;
	}
	public void setProgressReport(String progressReport) {
		this.progressReport = progressReport;
	}
	public String getMessageFooter() {
		return MessageFooter;
	}
	public String getSenderName() {
		return SenderName;
	}
	public String getTeacherFirstName() {
		return teacherFirstName;
	}
	public void setTeacherFirstName(String teacherFirstName) {
		this.teacherFirstName = teacherFirstName;
	}
	public String getTeacherLastName() {
		return teacherLastName;
	}
	public void setTeacherLastName(String teacherLastName) {
		this.teacherLastName = teacherLastName;
	}
	
	public void send() {
		// Email to send to
		String email = ReceiverEmail;
		// Email subject
		String subject = Subject;
		// The email message body
		String messageBody = MessageHeader + pupilName + MessageHeader2 + progressReport + MessageFooter + teacherFirstName + " " + teacherLastName;
		// Calls createSessionObject
		Session session = createSessionObject();
		try {
			// the message to be sent
			javax.mail.Message message = createMessage(email, subject, messageBody, session);
			// send the message
			Transport.send(message);
			// catch any email sending exceptions
			System.out.println("Mail Sent");
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Email Sent");
			alert.setHeaderText("Email has been successfully sent");
			alert.showAndWait();
		} catch (MessagingException e) {
			alert.setTitle("Email Not Sent");
			alert.setHeaderText("Sorry there has been an error");
			alert.showAndWait();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			alert.setTitle("Email Not Sent");
			alert.setHeaderText("Sorry there has been an error");
			alert.showAndWait();
		}
	}
	/*
	 * Creates the email to be sent
	 */
	private javax.mail.Message createMessage(String email, String subject, String messageBody, Session session)
			throws MessagingException, UnsupportedEncodingException {
		javax.mail.Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(SenderEmail, SenderName));
		message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(email, email));
		message.setSubject(subject);
		message.setText(messageBody);
		return message;
	}
	/*
	 * This method authenticates the email sender
	 */
	private Session createSessionObject() {
		// the properties of the email server
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		return Session.getInstance(properties, new javax.mail.Authenticator() {
			// the validation for the account of the email sender
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SenderEmail, SenderPassword);
			}
		});
	}

}