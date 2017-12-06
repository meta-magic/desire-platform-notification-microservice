package com.desire3d.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.desire3d.channel.NotificationChannel;
import com.desire3d.event.UserLoginCreatedEvent;
import com.desire3d.notification.domain.service.Notification;

/**
 * @author Mahesh Pardeshi
 *
 */
@Component
public class UserLoginCreatedEventListener {

	private final Logger logger = LoggerFactory.getLogger(UserLoginCreatedEventListener.class);

	@Autowired
	private Notification notificationDomainService;

	@StreamListener(NotificationChannel.NOTIFICATION_INPUT)
	public void listen(final UserLoginCreatedEvent event) {
		logger.info("Message '{}' with type '{}' received ", event, event.getClass());
		String body = "Welcome <br>User Registered Successfully<br><br>Login ID: " + event.getLoginId() + "<br>" + "Password : " + event.getPassword();
		boolean emailStatus = notificationDomainService.sendEmail(event.getEmailId(), "User Registration", body, true);
		logger.info("Email sent with status '{}' ", emailStatus);
	}
}