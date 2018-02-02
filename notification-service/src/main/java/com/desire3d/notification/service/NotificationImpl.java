package com.desire3d.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.desire3d.notification.component.VelocityTemplateEngine;
import com.desire3d.notification.event.EmailNotificationEvent;
import com.desire3d.notification.exception.DataRetrievalFailureException;
import com.desire3d.notification.fw.repository.TemplateRepository;
import com.desire3d.notification.fw.service.Notification;
import com.desire3d.notification.utils.MailSender;

/**
 * Class to send Email, SMS & Push notifications 
 * 
 * @author Mahesh Pardeshi
 */
@Component
public final class NotificationImpl extends VelocityTemplateEngine implements Notification {

	private final Logger logger = LoggerFactory.getLogger(NotificationImpl.class);

	private final TemplateRepository templateRepository;

	public NotificationImpl(TemplateRepository templateRepository) {
		super();
		this.templateRepository = templateRepository;
	}

	/** 
	 * method to send email notification for raised event 
	 * 
	 * @param event {@link EmailNotificationEvent}
	 * @return email sending status(success/failure)
	 * */
	@Override
	public final boolean sendEmail(final EmailNotificationEvent event) {
		try {
			String body = prepareEmailContent(event);
			return MailSender.sendEmail(event.getToAddress(), event.getSubject(), body, event.getCc(), event.getBcc());
		} catch (Throwable e) {
			logger.error("Email sending failed : ", e);
			return false;
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