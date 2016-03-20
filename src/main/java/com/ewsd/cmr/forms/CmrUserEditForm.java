package com.ewsd.cmr.forms;

import com.ewsd.cmr.entity.CmrUser;
import com.ewsd.cmr.utils.DateUtils;

public class CmrUserEditForm extends AbstractEditForm {

	private static final long serialVersionUID = 6269330790655684198L;

	private int usrId;

	private String active;

	private String createdDate;

	private String dob;

	private String email;

	private String modifiedDate;

	private String telephone;

	private String usrFname;

	private String usrLname;

	private String usrName;

	private String usrPsw;

	private String newUsrPsw;

	private String confirmNewUsrPsw;

	private Integer[] roles;

	private Integer[] faculties;

	public CmrUserEditForm() {
		this.active = "Y";
	}

	public CmrUserEditForm(final CmrUser user) {
		this.usrId = user.getUsrId();
		this.createdDate = DateUtils.toDateString(user.getCreatedDate(),
				DateUtils.LONG_SWISS_DATE_PATTERN);
		this.modifiedDate = DateUtils.toDateString(user.getModifiedDate(),
				DateUtils.LONG_SWISS_DATE_PATTERN);
		this.active = user.getActive();
		this.dob = DateUtils.toDateString(user.getDob(),
				DateUtils.SHORT_SWISS_DATE_PATTERN);
		this.email = user.getEmail();
		this.telephone = user.getTelephone();
		this.usrFname = user.getUsrFname();
		this.usrLname = user.getUsrLname();
		this.usrName = user.getUsrName();
		this.usrPsw = user.getUsrPsw();
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsrFname() {
		return usrFname;
	}

	public void setUsrFname(String usrFname) {
		this.usrFname = usrFname;
	}

	public String getUsrLname() {
		return usrLname;
	}

	public void setUsrLname(String usrLname) {
		this.usrLname = usrLname;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public int getUsrId() {
		return usrId;
	}

	public String getUsrPsw() {
		return usrPsw;
	}

	public void setUsrPsw(String usrPsw) {
		this.usrPsw = usrPsw;
	}

	public String getNewUsrPsw() {
		return newUsrPsw;
	}

	public void setNewUsrPsw(String newUsrPsw) {
		this.newUsrPsw = newUsrPsw;
	}

	public String getConfirmNewUsrPsw() {
		return confirmNewUsrPsw;
	}

	public void setConfirmNewUsrPsw(String confirmNewUsrPsw) {
		this.confirmNewUsrPsw = confirmNewUsrPsw;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public Integer[] getRoles() {
		return roles;
	}

	public void setRoles(Integer[] roles) {
		this.roles = roles;
	}

	public Integer[] getFaculties() {
		return faculties;
	}

	public void setFaculties(Integer[] faculties) {
		this.faculties = faculties;
	}

}
