package com.desire3d.notification;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${oauth2.authentication.properties.tokenUrl}")
	public void setTokenUrl(String tokenUrl) {
		OAuthConfig.TOKEN_URL = tokenUrl;
	}

	@Value("${oauth2.authentication.properties.clientId}")
	public void setClientId(String clientId) {
		OAuthConfig.CLIENT_ID = clientId;
	}

	@Value("${oauth2.authentication.properties.clientSecret}")
	public void setClientSecret(String clientSecret) {
		OAuthConfig.CLIENT_SECRET = clientSecret;
	}

	@Value("${oauth2.authentication.properties.refreshToken}")
	public void setRefreshToken(String refreshToken) {
		OAuthConfig.REFRESH_TOKEN = refreshToken;
	}
}