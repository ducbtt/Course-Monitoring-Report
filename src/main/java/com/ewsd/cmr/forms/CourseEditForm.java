package com.ewsd.cmr.forms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ewsd.cmr.entity.CmrUser;
import com.ewsd.cmr.entity.Course;
import com.ewsd.cmr.utils.DateUtils;

public class CourseEditForm extends AbstractEditForm {

	private static final long serialVersionUID = 1509605113853168083L;

	private String crsCode;

	private int credits;

	private String crsDetails;

	private String crsName;

	private String endDate;

	private String preRequisite;

	private String startDate;

	private List<CmrUser> cls;

	private List<CmrUser> cms;

	private CmrUser cl;

	private CmrUser cm;

	private Integer clId;

	private Integer cmId;

	private Integer[] facultyIds;

	public CourseEditForm(Course course) {
		this.crsCode = course.getCrsCode();
		this.credits = course.getCredits();
		this.crsDetails = course.getCrsDetails();
		this.crsName = course.getCrsName();
		this.startDate = DateUtils.toDateString(course.getStartDate(),
				DateUtils.SHORT_SWISS_DATE_PATTERN);
		this.endDate = DateUtils.toDateString(course.getEndDate(),
				DateUtils.SHORT_SWISS_DATE_PATTERN);
		this.preRequisite = course.getPreRequisite();
		this.cl = course.getCmrUser1();
		this.cm = course.getCmrUser2();
		this.cls = new ArrayList<>();
		this.cms = new ArrayList<>();
		;
		this.clId = cl == null ? 0 : cl.getUsrId();
		this.cmId = cm == null ? 0 : cm.getUsrId();

	}

	public CourseEditForm() {
		Calendar cal = Calendar.getInstance();
		this.startDate = DateUtils.toDateString(cal.getTime(),
				DateUtils.SHORT_SWISS_DATE_PATTERN);
		cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		this.endDate = DateUtils.toDateString(cal.getTime(),
				DateUtils.SHORT_SWISS_DATE_PATTERN);
	}

	public String getCrsCode() {
		return crsCode;
	}

	public void setCrsCode(String crsCode) {
		this.crsCode = crsCode;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getCrsDetails() {
		return crsDetails;
	}

	public void setCrsDetails(String crsDetails) {
		this.crsDetails = crsDetails;
	}

	public String getCrsName() {
		return crsName;
	}

	public void setCrsName(String crsName) {
		this.crsName = crsName;
	}

	public String getPreRequisite() {
		return preRequisite;
	}

	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public CmrUser getCl() {
		return cl;
	}

	public void setCl(CmrUser cl) {
		this.cl = cl;
	}

	public CmrUser getCm() {
		return cm;
	}

	public void setCm(CmrUser cm) {
		this.cm = cm;
	}

	public List<CmrUser> getCls() {
		return cls;
	}

	public void setCls(List<CmrUser> cls) {
		this.cls = cls;
	}

	public List<CmrUser> getCms() {
		return cms;
	}

	public void setCms(List<CmrUser> cms) {
		this.cms = cms;
	}

	public Integer getClId() {
		return clId;
	}

	public void setClId(Integer clId) {
		this.clId = clId;
	}

	public Integer getCmId() {
		return cmId;
	}

	public void setCmId(Integer cmId) {
		this.cmId = cmId;
	}

	public Integer[] getFacultyIds() {
		return facultyIds;
	}

	public void setFacultyIds(Integer[] facultyIds) {
		this.facultyIds = facultyIds;
	}

}
