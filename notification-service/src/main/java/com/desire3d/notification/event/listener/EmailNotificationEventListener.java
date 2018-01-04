package com.desire3d.notification.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.desire3d.notification.channel.NotificationChannel;
import com.desire3d.notification.event.EmailNotificationEvent;
import com.desire3d.notification.fw.service.Notification;

/**
 * @author Mahesh Pardeshi
 *
 */
@Component
public class EmailNotificationEventListener {

	private final Logger logger = LoggerFactory.getLogger(EmailNotificationEventListener.class);

	@Autowired
	private Notification notification;

	@StreamListener(NotificationChannel.EMAIL_NOTIFICATION_INPUT_CHANNEL)
	public void listen(final EmailNotificationEvent event) {
		logger.info("Message '{}' with type '{}' received ", event, event.getClass());
		boolean emailStatus = notification.sendEmail(event);
		logger.info("Email sent with status '{}' ", emailStatus);
	}
}