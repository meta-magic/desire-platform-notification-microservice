package com.desire3d.notification.fw.service;

import com.desire3d.notification.event.EmailNotificationEvent;

/**
 * @author Mahesh Pardeshi
 *
 */
public interface Notification {

	public boolean sendEmail(EmailNotificationEvent event);
}