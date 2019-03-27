package com.desire3d.notification;

import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to read google-oauth2 properties
 * 
 * @author Mahesh Pardeshi
 *
 */
@Configuration
public class OAuthConfig {

	protected static String TOKEN_URL;

	protected static String CLIENT_ID;

	protected static String CLIENT_SECRET;

	protected static String REFRESH_TOKEN;

	protected static Long TOKEN_EXPIRES_AT = System.currentTimeMillis();

	protected static String ACCESS_TOKEN = null;

	public void setTokenUrl(String tokenUrl) {
		OAuthConfig.TOKEN_URL = System.getenv("OAUTH2_AUTHENTICATION_PROPERTIES_TOKENURL");
	}

	public void setClientId(String clientId) {
		OAuthConfig.CLIENT_ID = System.getenv("OAUTH2_AUTHENTICATION_PROPERTIES_CLIENTID");
	}

	public void setClientSecret(String clientSecret) {
		OAuthConfig.CLIENT_SECRET = System.getenv("OAUTH2_AUTHENTICATION_PROPERTIES_CLIENTSECRET");
	}

	public void setRefreshToken(String refreshToken) {
		OAuthConfig.REFRESH_TOKEN = System.getenv("OAUTH2_AUTHENTICATION_PROPERTIES_REFRESHTOKEN");
	}
}