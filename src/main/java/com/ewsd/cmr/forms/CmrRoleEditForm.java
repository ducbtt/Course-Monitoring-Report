package com.ewsd.cmr.forms;

import com.ewsd.cmr.entity.CmrRole;

public class CmrRoleEditForm extends AbstractEditForm {

	private static final long serialVersionUID = 7316817153605939612L;

	private int roleId;

	private String description;

	private String name;

	public CmrRoleEditForm() {
	}

	public CmrRoleEditForm(CmrRole role) {
		this.roleId = role.getRoleId();
		this.name = role.getName();
		this.description = role.getDescription();
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
