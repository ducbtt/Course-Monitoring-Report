package com.ewsd.cmr.forms;

import java.io.Serializable;

public abstract class AbstractEditForm implements Serializable {

	private static final long serialVersionUID = 8507064032219458513L;

	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isCreatedForm() {
		return "create".equals(action);
	}
	
	public boolean isUpdatedForm() {
		return "update".equals(action);
	}

}
