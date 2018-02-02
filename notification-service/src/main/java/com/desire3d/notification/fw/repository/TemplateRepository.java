package com.desire3d.notification.fw.repository;

import java.util.Collection;

import com.desire3d.notification.exception.DataRetrievalFailureException;
import com.desire3d.notification.exception.PersistenceFailureException;
import com.desire3d.notification.model.Template;

/**
 * @author Mahesh Pardeshi
 *
 */
public interface TemplateRepository {

	public Template save(Template template) throws PersistenceFailureException;

	public void update(Template template) throws PersistenceFailureException;

	public void delete(String id) throws PersistenceFailureException;

	public Collection<Template> findAll() throws DataRetrievalFailureException;

	public Template findById(String id) throws DataRetrievalFailureException;

}