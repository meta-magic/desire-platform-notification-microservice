package com.desire3d.notification;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.desire3d.notification.event.EmailNotificationEvent;
import com.desire3d.notification.fw.service.Notification;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceApplicationTests {

	@Autowired
	private Notification notification;

	@Test
	public void contextLoads() {
		Set<String> cc = new HashSet<>();
		cc.add("maheshpardeshi17@gmail.com");
		Map<String, Object> dynamicContent = new HashMap<>();
		dynamicContent.put("username", "Mahesh");
		dynamicContent.put("loginid", "maheshpardeshi");
		notification.sendEmail(new EmailNotificationEvent("mahesh.pardeshi@metamagic.in", "Test Email", cc, null, "a5cbc718-c650-440d-a23d-f2185b80f431", dynamicContent));
	}
}