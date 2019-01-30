package com.xinwei.shiro.tag;

public class LacksRoleTag extends RoleTag {
	protected boolean showTagBody(String roleName) {
		boolean hasRole = getSubject() != null && getSubject().hasRole(roleName);
		return !hasRole;
	}
}
