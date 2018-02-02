package com.desire3d.notification.component;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.desire3d.notification.OAuthConfig;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * This class is used to renew the OAuth2 access token 
 * 
 * @author Mahesh Pardeshi
 *
 */
public abstract class OAuthHelper extends OAuthConfig {

	private static final Logger logger = LoggerFactory.getLogger(OAuthHelper.class);

	/**
	 * method used to renew google access token
	 * @return the remaining lifetime in seconds of the access token (for example 3600 for an hour, or
	 * -3600 if expired an hour ago) or {@code null} if unknown.
	 * @throws IOException 
	 *  
	 * */
	public static Long renewGoogleOAuthAccessToken() throws IOException {
		logger.info("Google Authentication Access-Token renewal process started");
		final JsonFactory jsonFactory = new JacksonFactory();
		final HttpTransport transport = new NetHttpTransport();

		final GoogleCredential googleCredential = new GoogleCredential.Builder()
				.setClientSecrets(CLIENT_ID, CLIENT_SECRET)
				.setJsonFactory(jsonFactory)
				.setTransport(transport)
				.build()
				.setRefreshToken(REFRESH_TOKEN);

		/* Request a new token */
		googleCredential.refreshToken();

		ACCESS_TOKEN = googleCredential.getAccessToken();
		logger.info("Google Authentication Access-Token renewal process completed");
		return googleCredential.getExpiresInSeconds();
	}

	/** 
	 * method used to generate an ACCESS-TOKEN at first and get it for authentication
	 *
	 * @return ACCESS_TOKEN
	 * @throws IOException */
	public static String getAccessToken() throws IOException {
		if (ACCESS_TOKEN == null) {
			renewGoogleOAuthAccessToken();
		}
		return ACCESS_TOKEN;
	}
}