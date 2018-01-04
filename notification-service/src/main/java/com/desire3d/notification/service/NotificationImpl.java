package com.desire3d.notification.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.desire3d.notification.component.VelocityTemplateEngine;
import com.desire3d.notification.event.EmailNotificationEvent;
import com.desire3d.notification.exception.DataRetrievalFailureException;
import com.desire3d.notification.fw.repository.TemplateRepository;
import com.desire3d.notification.fw.service.Notification;

/**
 * Class to send Email, SMS & Push notifications 
 * 
 * @author Mahesh Pardeshi
 */
@Component
public final class NotificationImpl extends VelocityTemplateEngine implements Notification {

	private final Logger logger = LoggerFactory.getLogger(NotificationImpl.class);

	private final JavaMailSender javaMailSender;

	private final TemplateRepository templateRepository;

	public NotificationImpl(JavaMailSender javaMailSender, TemplateRepository templateRepository) {
		super();
		this.javaMailSender = javaMailSender;
		this.templateRepository = templateRepository;
	}

	/** 
	 * method to send email notification for raised event 
	 * 
	 * @param event {@link EmailNotificationEvent}
	 * @return email sending status(success/failure)
	 * */
	@Async
	@Override
	public final boolean sendEmail(final EmailNotificationEvent event) {
		try {
			final MimeMessage message = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper messageHelper = new MimeMessageHelper(message);
			messageHelper.setTo(event.getToAddress().trim());
			messageHelper.setSubject(event.getSubject());
			this.addCc(event, messageHelper);
			this.addBcc(event, messageHelper);
			messageHelper.setText(prepareEmailContent(event), true);
			javaMailSender.send(message);
			logger.info("Email has been sent successfully...");
			return true;
		} catch (Throwable e) {
			logger.error("Email sending failed : ", e);
			return false;
		}
	}

	/**
	 * Method to add carbon copy to secondary recipients to underlying {@link MimeMessage}
	 * 
	 * @param event {@link EmailNotificationEvent}
	 * @param messageHelper
	 * @throws MessagingException  
	 * */
	private void addCc(final EmailNotificationEvent event, final MimeMessageHelper messageHelper) throws MessagingException {
		if (event.getCc() != null) {
			for (final String cc : event.getCc()) {
				messageHelper.addCc(cc.trim());
			}
		}
	}

	/**
	 * Method to add bcc blind carbon copy to secondary recipients to underlying {@link MimeMessage}
	 * 
	 * @param event {@link EmailNotificationEvent}
	 * @param messageHelper
	 * @throws MessagingException  
	 * */
	private void addBcc(final EmailNotificationEvent event, final MimeMessageHelper messageHelper) throws MessagingException {
		if (event.getBcc() != null) {
			for (final String bcc : event.getBcc()) {
				messageHelper.addBcc(bcc.trim());
			}
		}
	}

	/** 
	 * method to find & prepare email body content using velocity template engine 
	 * 
	 * @param event
	 * @return {@link String} dynamic email content using velocity template
	 * @throws DataRetrievalFailureException 
	 * */
	private String prepareEmailContent(EmailNotificationEvent event) throws DataRetrievalFailureException {
		String content = templateRepository.findById(event.getTemplateId()).getContent();
		return mergeTemplate(content, event.getDynamicContent());
	}
}