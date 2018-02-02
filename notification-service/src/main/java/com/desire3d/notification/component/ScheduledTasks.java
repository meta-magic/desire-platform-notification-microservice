package com.desire3d.notification.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.desire3d.notification.event.EmailNotificationEvent;
import com.desire3d.notification.fw.service.Notification;

/**
 * Class to schedule the task
 *  
 * @author Mahesh Pardeshi
 *
 */
@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	private Notification notification;

	private final String TEMPLATE_ID_FOR_LOGINID_NOTIFICATION = "a5cbc718-c650-440d-a23d-f2185b80f431";

	/** Scheduled task to renew google access token */
	@Scheduled(fixedRate = 50000)
	public void scheduleTokenRenew() {
		logger.info("Google Authentication Access-Token Renewal Task Started");
		try {
			Long expiresInSeconds = OAuthHelper.renewGoogleOAuthAccessToken();
			logger.info("Google Authentication Access-Token Renewal Task Completed");
			try {
				TimeUnit.SECONDS.sleep(expiresInSeconds - TimeUnit.MINUTES.toSeconds(10));
			} catch (InterruptedException ex) {
				logger.error("Google Authentication Access Token Renewal Task Failed, Ran into an error {}", ex.getMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	@Scheduled(fixedDelay = 50000, initialDelay = 5000)
	public void scheduleTokenRenewTest() {
		Map<String, Object> dynamicContent = new HashMap<>();
		dynamicContent.put("username", "Mahesh");
		dynamicContent.put("loginid", "maheshp");

		EmailNotificationEvent event = new EmailNotificationEvent("maheshpardeshi17@gmail.com", "Test email", TEMPLATE_ID_FOR_LOGINID_NOTIFICATION,
				dynamicContent);
		notification.sendEmail(event);
	}
}