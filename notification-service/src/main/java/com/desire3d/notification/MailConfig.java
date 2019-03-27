package com.desire3d.notification;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${mail.smtp.host}")
	public void setHost(String host) {
		MailConfig.HOST = host;
	}

	@Value("${mail.smtp.username}")
	public void setUsername(String username) {
		MailConfig.USER_NAME = username;
	}

	@Value("${mail.debug}")
	public void setDebug(boolean debug) {
		MailConfig.DEBUG = debug;
	}

	@Value("${mail.connectiontimeout}")
	public void setConnectiontimeout(Integer connectiontimeout) {
		MailConfig.CONNECTION_TIMEOUT = connectiontimeout;
	}

	@Value("${mail.timeout}")
	public void setTimeout(Integer timeout) {
		MailConfig.SOCKET_TIMEOUT = timeout;
	}
}