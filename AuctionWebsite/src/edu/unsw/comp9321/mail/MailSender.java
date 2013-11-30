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

public class MailSender {
	String senderAddress;
	String senderPass;
	String uName;
	String url;
	Random randomGenerator;
	int val;

	public MailSender() {
		senderAddress = "webappass2@gmail.com";
		senderPass = "webappass2ybilge";
		uName = "webappass2";
		url = "";
		randomGenerator = new Random();
		val = randomGenerator.nextInt(100);
	}

	public int getVal() {
		return val;
	}

	public void sendMail(String to) throws AddressException,
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
		//val = randomGenerator.nextInt(100);
		url = "http://localhost:8080/AuctionWebsite/controller?action=registrationCompleted&val="
				+ val;
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(senderAddress));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
		message.setSubject("Auction System Registration");
		message.setText("Dear User," + "\n\n Please go to following url\n"
				+ url);
		Transport.send(message);
		System.out.println("Done");

	}

}
