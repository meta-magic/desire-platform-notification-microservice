package com.desire3d.notification;

import java.io.IOException;

import com.desire3d.notification.component.OAuthHelper;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class OAuth2Test {

	//	@Test
	public void testGeneration() {
		try {
			System.out.println("**********ACCESS TOKEN : " + OAuthHelper.getAccessToken());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	@Test
	public void testRenewal() {
		try {
			OAuthHelper.renewGoogleOAuthAccessToken();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}