package com.desire3d.notification;

import java.util.HashMap;
import java.util.Map;

import com.desire3d.notification.event.EmailNotificationEvent;
import com.desire3d.notification.fw.service.Notification;

/**
 * @author Mahesh Pardeshi
 *
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class EmailTest {

//	@Autowired
	private Notification notification;

	private final String TEMPLATE_ID_FOR_LOGINID_NOTIFICATION = "a5cbc718-c650-440d-a23d-f2185b80f431";
	private final String TEMPLATE_ID_FOR_PASSWORD_NOTIFICATION = "b5cbc718-c650-440d-a23d-f2185b80f432";
	private final String TEMPLATE_ID_FOR_FORGOT_PASSWORD_NOTIFICATION = "c5cbc718-c650-440d-a23d-f2185b80f433";
	private final String TEMPLATE_ID_FOR_SIGNUP_NOTIFICATION = "d5cbc718-c650-440d-a23d-f2185b80f434";

	//	@Test
	public void testLoginIdEmail() {
		Map<String, Object> dynamicContent = new HashMap<>();
		dynamicContent.put("username", "Araf Karsh");
		dynamicContent.put("loginid", "araf.karsh");

		EmailNotificationEvent event = new EmailNotificationEvent("araf.karsh@metamagic.in", TEMPLATE_ID_FOR_LOGINID_NOTIFICATION, dynamicContent);
		notification.sendEmail(event);
	}

	//	@Test
	public void testPasswordEmail() {
		Map<String, Object> dynamicContent = new HashMap<>();
		dynamicContent.put("username", "Araf Karsh");
		dynamicContent.put("password", "Araf@334");

		EmailNotificationEvent event = new EmailNotificationEvent("araf.karsh@metamagic.in", TEMPLATE_ID_FOR_PASSWORD_NOTIFICATION, dynamicContent);
		notification.sendEmail(event);
	}

	//	@Test
	public void testForgotPassword() {
		Map<String, Object> dynamicContent = new HashMap<>();
		dynamicContent.put("username", "Araf Karsh");
		dynamicContent.put("token", "SAMPLEXXXYYYZZZTOKEN");
		dynamicContent.put("tokenexpiry", "10");

		EmailNotificationEvent event = new EmailNotificationEvent("araf.karsh@metamagic.in", TEMPLATE_ID_FOR_FORGOT_PASSWORD_NOTIFICATION, dynamicContent);
		notification.sendEmail(event);
	}

	//	@Test
	public void testSignupEmail() {
		Map<String, Object> dynamicContent = new HashMap<>();
		dynamicContent.put("username", "Mahesh");

		EmailNotificationEvent event = new EmailNotificationEvent("maheshpardeshi17@gmail.com", TEMPLATE_ID_FOR_SIGNUP_NOTIFICATION, dynamicContent);
		notification.sendEmail(event);
	}
}