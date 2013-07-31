package br.puc.rio.jpedro.monetize.email;

import android.util.Log;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail
{
    // Variable TAG used for debugging reasons
    private static final String TAG = "SendEmail";

	public static void sendEmail ( String number, String txtMsg )
	{
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("monetizeapp@gmail.com","MonetizeApp$$");
                    }
                });

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("monetizeapp@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("joaopedro.pinheiro88@gmail.com"));
            message.setSubject("[MonetizeApp] Unable to Parse");
            message.setText("Number : " + number + "\n\n Message: " + txtMsg);
            Transport.send(message);
            System.out.println("Done");
        }
        catch (MessagingException ex1)
        {  
        	Log.e(TAG, "Unable to send email " + ex1.getMessage());
        }
        catch (NullPointerException ex2)
        {
            Log.e(TAG, "Unable to find user or password " + ex2.getMessage());
        }
    }
}