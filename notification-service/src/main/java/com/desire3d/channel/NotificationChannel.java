package com.desire3d.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Mahesh Pardeshi
 *
 */
public interface NotificationChannel {

	String NOTIFICATION_INPUT = "notificationInputChannel";

	@Input(NOTIFICATION_INPUT)
	SubscribableChannel notificationInputChannel();

}