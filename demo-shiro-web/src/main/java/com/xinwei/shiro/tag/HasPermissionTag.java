package com.xinwei.shiro.tag;

public class HasPermissionTag extends PermissionTag {
	protected boolean showTagBody(String p) {
		return isPermitted(p);
	}
}
