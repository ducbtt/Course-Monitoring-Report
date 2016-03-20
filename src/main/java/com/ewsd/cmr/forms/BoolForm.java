package com.ewsd.cmr.forms;

import java.io.Serializable;

public class BoolForm implements Serializable {

	private static final long serialVersionUID = 957343199864024331L;

	private String desc;

	private String value;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BoolForm(String value, String desc) {
		super();
		this.desc = desc;
		this.value = value;
	}

	public BoolForm() {
	}
}
