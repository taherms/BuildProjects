package firstTime;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class sendMail {
 public void sendEmail(String subject,String msg) {

	// Recipient's email ID needs to be mentioned.
     String[] to = {"tahershabbiri@gmail.com","hatimjuzerkanorwala@gmail.com"};

     // Sender's email ID needs to be mentioned
     String from = "tahershabbiri@gmail.com";

     // Assuming you are sending email from through gmails smtp
     String host = "smtp.gmail.com";
     

     // Get system properties
     Properties properties = System.getProperties();

     // Setup mail server
     properties.put("mail.smtp.host", host);
     properties.put("mail.smtp.port", "587");
     properties.put("mail.smtp.starttls.enable", "true");
     properties.put("mail.smtp.ssl.enable", "true");
     properties.put("mail.smtp.auth", "true");

     // Get the Session object.// and pass username and password
     Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication("usergamca@gmail.com", "3PZVCFqw");
         }
     });

     // Used to debug SMTP issues
     session.setDebug(true);

     try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         for (String t : to) {
        	 message.addRecipient(Message.RecipientType.TO, new InternetAddress(t));
		}
         

         // Set Subject: header field
         message.setSubject(subject);

         // Now set the actual message
         message.setText(msg);

        // System.out.println("sending...");
         // Send message
			/*
			 * Transport transport = session.getTransport("smtp"); transport.connect(host,
			 * from, pass); transport.sendMessage(message, message.getAllRecipients());
			 * transport.close();
			 */
         Transport.send(message);
         //System.out.println("Sent message successfully....");
     } catch (MessagingException mex) {
         mex.printStackTrace();
     }
	 
	 
 }
}