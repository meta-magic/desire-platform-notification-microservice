package com.desire3d.notification;

import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to read Mail properties
 * 
 * @author Mahesh Pardeshi
 *
 */
@Configuration
public class MailConfig {

	public static String HOST;

	public static String USER_NAME;

	public static boolean DEBUG;

	public static Integer CONNECTION_TIMEOUT;

	public static Integer SOCKET_TIMEOUT;

	public static final boolean STARTTLS = true;

	public static final Integer PORT = 587;

	public void setHost(String host) {
		MailConfig.HOST = System.getenv("MAIL_SMTP_HOST");
	}

	public void setUsername(String username) {
		MailConfig.USER_NAME = System.getenv("MAIL_SMTP_USERNAME");
	}

	public void setDebug(boolean debug) {
		MailConfig.DEBUG = Boolean.valueOf(System.getenv("MAIL_DEBUG"));
	}

	public void setConnectiontimeout(Integer connectiontimeout) {
		MailConfig.CONNECTION_TIMEOUT = Integer.valueOf(System.getenv("MAIL_CONNECTIONTIMEOUT"));
	}

	public void setTimeout(Integer timeout) {
		MailConfig.SOCKET_TIMEOUT = Integer.valueOf(System.getenv("MAIL_TIMEOUT"));
	}
}