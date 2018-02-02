package com.desire3d.notification.repository;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.desire3d.notification.exception.DataRetrievalFailureException;
import com.desire3d.notification.exception.PersistenceFailureException;
import com.desire3d.notification.fw.repository.TemplateRepository;
import com.desire3d.notification.model.Template;
import com.desire3d.notification.utils.ExceptionID;

/**
 * @author Mahesh Pardeshi
 *
 */
@Repository
public class TemplateRepositoryImpl implements TemplateRepository {

	@Autowired
	private PersistenceManagerFactory pmf;

	@Override
	public Template save(Template template) throws PersistenceFailureException {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			template = pm.makePersistent(template);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new PersistenceFailureException(ExceptionID.ERROR_SAVE, e);
		} finally {
			pm.close();
		}
		return template;
	}
	
	@Override
	public void update(Template template) throws PersistenceFailureException {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(template);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new PersistenceFailureException(ExceptionID.ERROR_UPDATE, e);
		} finally {
			pm.close();
		}
	}

	@Override
	public void delete(String id) throws PersistenceFailureException {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.deletePersistent(pm.getObjectById(Template.class, id));
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new PersistenceFailureException(ExceptionID.ERROR_DELETE, e);
		} finally {
			pm.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Template> findAll() throws DataRetrievalFailureException {
		PersistenceManager pm = pmf.getPersistenceManager();
		try {
			Query query = pm.newQuery(Template.class);
			Collection<Template> queryResult = (Collection<Template>) query.execute();
			return pm.detachCopyAll(queryResult);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataRetrievalFailureException(ExceptionID.ERROR_RETRIEVE, e);
		} finally {
			pm.close();
		}
	}

	@Override
	public Template findById(String id) throws DataRetrievalFailureException {
		PersistenceManager pm = pmf.getPersistenceManager();
		try {
			return pm.detachCopy(pm.getObjectById(Template.class, id));
		} catch (Exception e) {
			throw new DataRetrievalFailureException(ExceptionID.ERROR_RETRIEVE, e);
		} finally {
			pm.close();
		}
	}
}