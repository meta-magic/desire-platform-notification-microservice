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

	protected static String TOKEN_URL = System.getenv("OAUTH2_AUTHENTICATION_PROPERTIES_TOKENURL");

	protected static String CLIENT_ID = System.getenv("OAUTH2_AUTHENTICATION_PROPERTIES_CLIENTID");

	protected static String CLIENT_SECRET = System.getenv("OAUTH2_AUTHENTICATION_PROPERTIES_CLIENTSECRET");

	protected static String REFRESH_TOKEN = System.getenv("OAUTH2_AUTHENTICATION_PROPERTIES_REFRESHTOKEN");

	protected static Long TOKEN_EXPIRES_AT = System.currentTimeMillis();

	protected static String ACCESS_TOKEN = null;

}