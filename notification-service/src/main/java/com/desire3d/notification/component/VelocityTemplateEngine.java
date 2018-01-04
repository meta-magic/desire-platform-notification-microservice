package com.desire3d.notification.component;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.desire3d.notification.fw.component.TemplateEngine;

/**
 * Velocity Template implementation to merge template with dynamic content
 * 
 * @author Mahesh Pardeshi
 */
public class VelocityTemplateEngine implements TemplateEngine {

	private static final String TEMPLATE_NAME = "NOTIFICATION_TEMPLATE";

	@Override
	/** 
	 * method to merge template content and dynamic data i.e context
	 * 
	 *  @param content template 
	 *  @param context dynamic data 
	 *  @return merged template
	 * */
	public final String mergeTemplate(String content, Map<String, Object> context) {
		VelocityContext velocityContext = new VelocityContext(context);
		StringWriter sw = new StringWriter();
		Velocity.evaluate(velocityContext, sw, TEMPLATE_NAME, content);
		return sw.toString();
	}
}