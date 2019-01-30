package com.xinwei.shiro.tag;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.Map;

public class PrincipalTag extends SecureTag {
	static final Logger log = Logger.getLogger("PrincipalTag");

	String getType(Map params) {
		return getParam(params, "type");
	}

	String getProperty(Map params) {
		return getParam(params, "property");
	}

	@SuppressWarnings("unchecked")
	public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
		String result = null;
		if (getSubject() != null) {
			// Get the principal to print out
			Object principal;
			if (getType(params) == null) {
				principal = getSubject().getPrincipal();
			} else {
				principal = getPrincipalFromClassName(params);
			}
			// Get the string value of the principal
			if (principal != null) {
				String property = getProperty(params);
				if (property == null) {
					result = principal.toString();
				} else {
					result = getPrincipalProperty(principal, property);
				}
			}
		}
		// Print out the principal value if not null
		if (result != null) {
			try {
				env.getOut().write(result);
			} catch (IOException ex) {
				throw new TemplateException("Error writing [" + result + "] to Freemarker.", ex, env);
			}
		}
	}

	@SuppressWarnings("unchecked")
	Object getPrincipalFromClassName(Map params) {
		String type = getType(params);
		try {
			Class cls = Class.forName(type);
			return getSubject().getPrincipals().oneByType(cls);
		} catch (ClassNotFoundException ex) {
			log.error("Unable to find class for name [" + type + "]", ex);
		}
		return null;
	}

	String getPrincipalProperty(Object principal, String property) throws TemplateModelException {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(principal.getClass());
			// Loop through the properties to get the string value of the
			// specified property
			for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
				if (propertyDescriptor.getName().equals(property)) {
					Object value = propertyDescriptor.getReadMethod().invoke(principal, (Object[]) null);
					return String.valueOf(value);
				}
			}
			// property not found, throw
			throw new TemplateModelException("Property [" + property + "] not found in principal of type ["
					+ principal.getClass().getName() + "]");
		} catch (Exception ex) {
			throw new TemplateModelException("Error reading property [" + property + "] from principal of type ["
					+ principal.getClass().getName() + "]", ex);
		}
	}
}