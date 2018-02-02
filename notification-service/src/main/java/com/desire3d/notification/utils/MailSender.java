package com.desire3d.notification.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.desire3d.notification.MailConfig;
import com.desire3d.notification.component.OAuthHelper;

/**
 * Utility class to send emails
 * 
 * @author Mahesh Pardeshi
 *
 */
public abstract class MailSender {

	private static final Logger logger = LoggerFactory.getLogger(MailSender.class);

	/** 
	 * method to send MIME style email message 
	 * 
	 * @param to Recipients address
	 * @param subject of email 
	 * @param body content of email
	 * 
	 * */
	public static boolean sendEmail(String to, String subject, String body) {
		try {
			sendEmail(createMimeMessage(to, subject, body));
			return true;
		} catch (MessagingException | IOException mex) {
			mex.printStackTrace();
			return false;
		}
	}

	/** 
	 * method to send MIME style email message with cc
	 * 
	 * @param to Recipients address
	 * @param subject of email 
	 * @param body content of email
	 * @param cc carbon copy
	 * 
	 * @return	status
	 * */
	public static boolean sendEmail(String to, String subject, String body, Collection<String> cc) {
		try {
			MimeMessage mimeMessage = createMimeMessage(to, subject, body);
			addCc(cc, mimeMessage);
			sendEmail(mimeMessage);
			return true;
		} catch (MessagingException | IOException mex) {
			mex.printStackTrace();
			return false;
		}
	}

	/** 
	 * method to send MIME style email message with cc & bcc 
	 * 
	 * @param to Recipients address
	 * @param subject of email 
	 * @param body content of email
	 * @param cc carbon copy
	 * @param bcc blind carbon copy
	 * 
	 * @return status
	 * */
	public static boolean sendEmail(String to, String subject, String body, Collection<String> cc, Collection<String> bcc) {
		try {
			MimeMessage mimeMessage = createMimeMessage(to, subject, body);
			addCc(cc, mimeMessage);
			addBcc(bcc, mimeMessage);
			sendEmail(mimeMessage);
			return true;
		} catch (MessagingException | IOException mex) {
			mex.printStackTrace();
			return false;
		}
	}

	/** 
	 * method to create {@link MimeMessage} with data passed 
	 * 
	 * @param to Recipients address
	 * @param subject of email 
	 * @param body content of email
	 * 
	 * @return {@link MimeMessage}
	 * @throws MessagingException 
	 * @throws IOException 
	 * */
	private static MimeMessage createMimeMessage(String to, String subject, String body) throws MessagingException, IOException {
		MimeMessage mimeMessage = new MimeMessage(getSession());
		mimeMessage.setFrom(new InternetAddress(MailConfig.USER_NAME));
		mimeMessage.setRecipients(Message.RecipientType.TO, to);
		mimeMessage.setSubject(subject);
		mimeMessage.setContent(body, "text/html; charset=utf-8");
		return mimeMessage;
	}

	/**
	 * method to send MIME style email message 
	 * 
	 * @param mimeMessage
	 * @throws MessagingException 
	 * @throws IOException 
	 * */
	private static void sendEmail(final MimeMessage mimeMessage) throws MessagingException, IOException {
		Transport.send(mimeMessage, MailConfig.USER_NAME, OAuthHelper.getAccessToken());
		logger.info("Email sent successfully.");
	}

	/**
	 * Method to add carbon copy as secondary recipients to underlying {@link MimeMessage}
	 * 
	 * @param cc
	 * @param mimeMessage
	 * @throws MessagingException 
	 * 
	 * */
	private static void addCc(Collection<String> cc, final MimeMessage mimeMessage) throws MessagingException {
		if (cc != null) {
			for (final String element : cc) {
				mimeMessage.addRecipients(Message.RecipientType.CC, element);
			}
		}
	}

	/**
	 * Method to add blind carbon copy as secondary recipients to underlying {@link MimeMessage}
	 * 
	 * @param bcc
	 * @param mimeMessage
	 * @throws MessagingException 
	 * 
	 * */
	private static void addBcc(Collection<String> bcc, final MimeMessage mimeMessage) throws MessagingException {
		if (bcc != null) {
			for (final String element : bcc) {
				mimeMessage.addRecipients(Message.RecipientType.BCC, element);
			}
		}
	}

	/** 
	 * method to create session using oauth access token 
	 * 
	 * @throws IOException
	 */
	private static Session getSession() throws IOException {
		String accesstoken = OAuthHelper.getAccessToken();
		Session session = Session.getInstance(emailProps(), new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MailConfig.USER_NAME, accesstoken);
			}
		});
		return session;
	}

	/** method to get prepare email properties */
	private static Properties emailProps() {
		Properties props = new Properties();
		props.put("mail.smtp.host", MailConfig.HOST);
		props.put("mail.smtp.port", MailConfig.PORT);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", MailConfig.STARTTLS);
		props.put("mail.smtp.auth.mechanisms", "XOAUTH2");
		props.put("mail.debug", MailConfig.DEBUG);
		return props;
	}
}