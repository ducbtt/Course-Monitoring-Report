package com.ewsd.cmr.forms;

import java.io.Serializable;

public class LoginForm implements Serializable {

	private static final long serialVersionUID = -4750646954226021893L;

	private String email;

	private String password;

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
