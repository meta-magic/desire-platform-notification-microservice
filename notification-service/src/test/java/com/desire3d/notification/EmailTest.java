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

	private Map<String, Object> dynamicContent;

	//	@Before
	public void setUp() {
		dynamicContent = new HashMap<>();
		dynamicContent.put("username", "Mahesh");
		dynamicContent.put("loginid", "maheshp");
	}

	//	@Test
	public void test() {
		EmailNotificationEvent event = new EmailNotificationEvent("maheshpardeshi17@gmail.com", "Test email", TEMPLATE_ID_FOR_LOGINID_NOTIFICATION,
				dynamicContent);
		notification.sendEmail(event);
	}
}