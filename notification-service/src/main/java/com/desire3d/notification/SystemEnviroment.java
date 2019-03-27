package com.desire3d.notification;

public final class SystemEnviroment {

	public static String getUser() {
		return System.getenv("NOTIFICATION_MS_DB_USER");
	}

	public static String getPassword() {
		return System.getenv("NOTIFICATION_MS_DB_PASSWORD");
	}

	public static String getConnectionurl() {
		return System.getenv("NOTIFICATION_DB_CONNECTION_URL");
	}

	public static String getTokenKey() {
		return System.getenv("TOKEN_KEY");
	}

	public static Long getTokenValidity() {
		return Long.valueOf(System.getenv("TOKEN_VALIDITY"));
	}

	public static Integer getSessionexpiry() {
		return Integer.valueOf(System.getenv("SESSION_EXPIRY"));
	}

}
