package com.desire3d.notification.domain.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Class to send email, sms & push notifications 
 * @author Mahesh Pardeshi
 *
 */
@Component
public final class Notification {

	private final Logger logger = LoggerFactory.getLogger(Notification.class);

	private final JavaMailSender javaMailSender;

	public Notification(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}

	/** 
	 * method to send email 
	 * 
	 * @param to recipient of email 
	 * @param subject point so as to summarize what the email is about
	 * @param body the main part of an email message
	 * @param isHtml boolean field to indicate whether the email content is html format or not
	 * 
	 * @return email sending status(success/failure)
	 * */
	@Async
	public boolean sendEmail(String to, String subject, String body, boolean isHtml) {
		try {
			final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper mail = new MimeMessageHelper(mimeMessage);
			mail.setTo(to);
			mail.setSubject(subject);
			mail.setText(body, isHtml);
			javaMailSender.send(mimeMessage);
			logger.info("Email has been sent successfully...");
			return true;
		} catch (Exception e) {
			logger.error("Error: ", e);
			return false;
		}
	}

	/** 
	 * method to send email with cc & bcc 
	 * 
	 * @param to recipient of email 
	 * @param cc carbon copy to secondary recipients
	 * @param bcc blind carbon copy to secondary recipients
	 * @param subject point so as to summarize what the email is about
	 * @param body the main part of an email message
	 * @param isHtml boolean field to indicate whether the email content is html format or not
	 * 
	 * @return email sending status(success/failure)
	 * */
	@Async
	public boolean sendEmail(String to, String cc, String bcc, String subject, String body, boolean isHtml) {
		try {
			final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper mail = new MimeMessageHelper(mimeMessage);
			mail.setTo(to);
			if (cc != null) {
				mail.setCc(cc);
			}
			if (bcc != null) {
				mail.setBcc(bcc);
			}
			mail.setSubject(subject);
			mail.setText(body, isHtml);
			javaMailSender.send(mimeMessage);
			logger.info("Email has been sent successfully...");
			return true;
		} catch (Exception e) {
			logger.error("Error: ", e);
			return false;
		}
	}
}
