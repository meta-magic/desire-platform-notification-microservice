package com.desire3d.notification.fw.component;

import java.util.Map;

/**
 * Abstraction of template engine to provide multiple implementations i.e Velocity Template & FreeMarker
 * 
 * @author Mahesh Pardeshi
 */
public interface TemplateEngine {

	public String mergeTemplate(String content, Map<String, Object> context);

}