package com.desire3d.notification.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * NOTIFICATION CHANNEL 
 * 
 * @author Mahesh Pardeshi
 */
public interface NotificationChannel {

	String EMAIL_NOTIFICATION_INPUT_CHANNEL = "emailNotificationInputChannel";

	@Input(EMAIL_NOTIFICATION_INPUT_CHANNEL)
	SubscribableChannel emailNotificationInputChannel();

}