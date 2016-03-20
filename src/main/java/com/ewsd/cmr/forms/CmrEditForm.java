package com.ewsd.cmr.forms;

import com.ewsd.cmr.entity.Cmr;
import com.ewsd.cmr.entity.CmrStatus;
import com.ewsd.cmr.entity.CmrUser;
import com.ewsd.cmr.entity.Course;
import com.ewsd.cmr.utils.DateUtils;

public class CmrEditForm extends AbstractEditForm {

	private static final long serialVersionUID = -6606092415709891569L;

	private int cmrId;
	
	private String allCredited;

	private String cmrNote;

	private String createdDate;

	private String crsSubmitDate;

	private Integer fbNoEnough = 0;

	private Integer fbNoExcellent = 0;

	private Integer fbNoGood = 0;

	private Integer fbNoPoor = 0;

	private Integer fbNoVeryGood = 0;

	private Integer fbNoVeryPoor = 0;

	private String modifiedDate;

	private Integer noStdGreen = 0;

	private Course course;

	private CmrStatus cmrStatusBean;

	private CmrUser cmrUser;

	private String crsCode;
	
	private String crsDetails;
	
	private boolean showed;
	
	private String comments;
	
	private boolean enableForApprove = true;
	
	public CmrEditForm() {
	}

	public CmrEditForm(final Cmr cmr) {
		this.cmrId = cmr.getCmrId();
		this.allCredited = cmr.getAllCredited();
		this.cmrNote = cmr.getCmrNote();
		this.createdDate = DateUtils.toDateString(cmr.getCreatedDate(), DateUtils.LONG_SWISS_DATE_PATTERN);
		this.crsSubmitDate = DateUtils.toDateString(cmr.getCrsSubmitDate(), DateUtils.LONG_SWISS_DATE_PATTERN);
		this.fbNoExcellent = cmr.getFbNoExcellent();
		this.fbNoVeryGood = cmr.getFbNoVeryGood();
		this.fbNoGood = cmr.getFbNoGood();
		this.fbNoEnough = cmr.getFbNoEnough();
		this.fbNoPoor = cmr.getFbNoPoor();
		this.fbNoVeryPoor = cmr.getFbNoVeryPoor();
		this.modifiedDate = DateUtils.toDateString(cmr.getModifiedDate(), DateUtils.LONG_SWISS_DATE_PATTERN);
		this.noStdGreen = cmr.getNoStdGreen();
		this.course = cmr.getCourse();
		this.crsCode = this.course.getCrsCode();
		this.crsDetails = this.course.getCrsDetails();
		this.cmrStatusBean = cmr.getCmrStatusBean();
		this.cmrUser = cmr.getCmrUser();
	}

	public int getCmrId() {
		return cmrId;
	}

	public void setCmrId(int cmrId) {
		this.cmrId = cmrId;
	}

	public String getAllCredited() {
		return allCredited;
	}

	public void setAllCredited(String allCredited) {
		this.allCredited = allCredited;
	}

	public String getCmrNote() {
		return cmrNote;
	}

	public void setCmrNote(String cmrNote) {
		this.cmrNote = cmrNote;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCrsSubmitDate() {
		return crsSubmitDate;
	}

	public void setCrsSubmitDate(String crsSubmitDate) {
		this.crsSubmitDate = crsSubmitDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public CmrStatus getCmrStatusBean() {
		return cmrStatusBean;
	}

	public void setCmrStatusBean(CmrStatus cmrStatusBean) {
		this.cmrStatusBean = cmrStatusBean;
	}

	public CmrUser getCmrUser() {
		return cmrUser;
	}

	public void setCmrUser(CmrUser cmrUser) {
		this.cmrUser = cmrUser;
	}

	public String getCrsCode() {
		return crsCode;
	}

	public void setCrsCode(String crsCode) {
		this.crsCode = crsCode;
	}

	public Integer getFbNoEnough() {
		return fbNoEnough;
	}

	public void setFbNoEnough(Integer fbNoEnough) {
		this.fbNoEnough = fbNoEnough;
	}

	public Integer getFbNoExcellent() {
		return fbNoExcellent;
	}

	public void setFbNoExcellent(Integer fbNoExcellent) {
		this.fbNoExcellent = fbNoExcellent;
	}

	public Integer getFbNoGood() {
		return fbNoGood;
	}

	public void setFbNoGood(Integer fbNoGood) {
		this.fbNoGood = fbNoGood;
	}

	public Integer getFbNoPoor() {
		return fbNoPoor;
	}

	public void setFbNoPoor(Integer fbNoPoor) {
		this.fbNoPoor = fbNoPoor;
	}

	public Integer getFbNoVeryGood() {
		return fbNoVeryGood;
	}

	public void setFbNoVeryGood(Integer fbNoVeryGood) {
		this.fbNoVeryGood = fbNoVeryGood;
	}

	public Integer getFbNoVeryPoor() {
		return fbNoVeryPoor;
	}

	public void setFbNoVeryPoor(Integer fbNoVeryPoor) {
		this.fbNoVeryPoor = fbNoVeryPoor;
	}

	public Integer getNoStdGreen() {
		return noStdGreen;
	}

	public void setNoStdGreen(Integer noStdGreen) {
		this.noStdGreen = noStdGreen;
	}

	public String getCrsDetails() {
		return crsDetails;
	}

	public void setCrsDetails(String crsDetails) {
		this.crsDetails = crsDetails;
	}

	public boolean isShowed() {
		return showed;
	}

	public void setShowed(boolean showed) {
		this.showed = showed;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isEnableForApprove() {
		return enableForApprove;
	}

	public void setEnableForApprove(boolean enableForApprove) {
		this.enableForApprove = enableForApprove;
	}

}
