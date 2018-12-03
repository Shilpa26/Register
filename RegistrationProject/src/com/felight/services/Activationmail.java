package com.felight.services;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Activationmail {
	public static void sender(int activationcode,String email,String Name) {
		final String username = "shilpachinni96@gmail.com";
		final String password = "Sanju@26shri";
	
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		try {
	
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("shilpachinni96@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Activate account :-)");
			message.setText("Hi, "+Name+" activate your account by clicking below link... \n http://localhost:8080/RegistrationProject/Activator?activationcode="+activationcode+"");	
			Transport.send(message);	
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
