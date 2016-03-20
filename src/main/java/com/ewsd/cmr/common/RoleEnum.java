package com.ewsd.cmr.common;

public enum RoleEnum {
	
	//TODO: find the dynamic mechanism
	CL("Course Leader"),
	CM("Course Moderator"),
	PVC("Pro-Vice Chancellor"),
	DLT("Director of Learning and Quality"),
	ADMIN("Administrator"),
	GUEST("Guest account"),
	STD("Student"),
	TEACH("Teacher");
	
	private final String desc;
	
	private RoleEnum(final String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	
}
