package com.ewsd.cmr.common;

public enum CmrStatusEnum {

	NEW("Created new by CL"),
	COMPLETED("Commented by DTL"),
	PENDING_CM_APPROVE("Pending for CM to approve"),
	PENDING_DTL_RESPONSE("Pending for DTL response");
	
	private String desc;
	
	private CmrStatusEnum(final String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
