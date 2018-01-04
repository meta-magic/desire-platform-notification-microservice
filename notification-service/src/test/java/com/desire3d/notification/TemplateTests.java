package com.desire3d.notification;

import java.util.Collection;
import java.util.Date;

import com.desire3d.notification.exception.DataRetrievalFailureException;
import com.desire3d.notification.exception.PersistenceFailureException;
import com.desire3d.notification.fw.repository.TemplateRepository;
import com.desire3d.notification.model.AuditDetails;
import com.desire3d.notification.model.Template;

/**
 * @author Mahesh Pardeshi
 *
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TemplateTests {

//	@Autowired
	private TemplateRepository repository;

//	@Test
	public void save() {
		Template template = new Template();
		template.setContent("Test Template");
		template.setAuditDetails(new AuditDetails("46d2558a-8e33-4acc-8a96-2d3b36bf59b3", new Date(), "46d2558a-8e33-4acc-8a96-2d3b36bf59b3", new Date()));
		try {
			repository.save(template);
		} catch (PersistenceFailureException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void update() {
		Template template = _findById(_findId());
		template.setContent("Update Content Test");
		template.getAuditDetails().setUpdatedBy("dbowner");
		template.getAuditDetails().setUpdatedTime(new Date());
		try {
			repository.update(template);
		} catch (PersistenceFailureException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void findById() {
		_findById(_findId());
	}

//	@Test
	public void findAll() {
		try {
			repository.findAll();
		} catch (DataRetrievalFailureException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void delete() {
		try {
			repository.delete(_findId());
		} catch (PersistenceFailureException e) {
			e.printStackTrace();
		}
	}

	private Template _findById(String id) {
		try {
			return repository.findById(id);
		} catch (DataRetrievalFailureException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String _findId() {
		String id = "";
		try {
			Collection<Template> results = repository.findAll();
			if (!results.isEmpty()) {
				id = results.iterator().next().getTemplateId();
			}
		} catch (DataRetrievalFailureException e) {
			e.printStackTrace();
		}
		return id;
	}
}