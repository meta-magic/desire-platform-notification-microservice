package com.desire3d.notification.model;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;

/**
 * @author Mahesh Pardeshi
 *
 */
@PersistenceCapable(table = "template", detachable = "true")
@Version(column = "VERSION", strategy = VersionStrategy.VERSION_NUMBER, extensions = {
		@Extension(vendorName = "datanucleus", key = "field-name", value = "version") })
public class Template implements Serializable {

	private static final long serialVersionUID = -131526722327717571L;

	@Persistent(customValueStrategy = "uuid")
	@PrimaryKey
	private String templateId;

	private String content;

	@Persistent
	private Boolean isActive = true;

	@Persistent
	private Long version;

	@Persistent(defaultFetchGroup = "true")
	private AuditDetails auditDetails;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public AuditDetails getAuditDetails() {
		return auditDetails;
	}

	public void setAuditDetails(AuditDetails auditDetails) {
		this.auditDetails = auditDetails;
	}

	public Long getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return "Template [templateId=" + templateId + ", content=" + content + ", isActive=" + isActive + ", auditDetails=" + auditDetails + "]";
	}
}