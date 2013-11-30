package edu.unsw.comp9321.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.unsw.comp9321.jdbc.ItemDTO;

public class CongratsMailSender {
	String senderAddress;
	String senderPass;
	String uName;
	String url;

	public CongratsMailSender() {
		senderAddress = "webappass2@gmail.com";
		senderPass = "webappass2ybilge";
		uName = "webappass2";
		url = "";
	}

	public void sendMail(String to, ItemDTO item) throws AddressException,
			MessagingException, UnsupportedEncodingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(uName, senderPass);
					}
				});
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(senderAddress));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
		message.setSubject("Cogratulations about your bid!!");
		message.setText("Dear User," + "\n\n You have won the auction by beating reserve price."
				+ "\n Here is your item details : "
				+"\n\t item name = " + item.getTitle()
				+"\n\t item description = " + item.getDescription());
		Transport.send(message);
		System.out.println("Done");

	}

}
