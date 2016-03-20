package com.ewsd.cmr.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the COURSE database table.
 *
 * @author lequoctruong
 */
@Entity
@NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CRS_CODE")
	private String crsCode;

	@Column(name = "CREDITS")
	private int credits;

	@Column(name = "CRS_DETAILS")
	private String crsDetails;

	@Column(name = "CRS_NAME")
	private String crsName;

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "PRE_REQUISITE")
	private String preRequisite;

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	private Date startDate;

	// bi-directional many-to-one association to Cmr
	@OneToMany(mappedBy = "course")
	private List<Cmr> cmrs;

	// bi-directional many-to-one association to CmrUser
	@ManyToOne
	@JoinColumn(name = "CRS_CL")
	private CmrUser cmrUser1;

	// bi-directional many-to-one association to CmrUser
	@ManyToOne
	@JoinColumn(name = "CRS_CM")
	private CmrUser cmrUser2;

	// bi-directional many-to-many association to Faculty
	@ManyToMany
	@JoinTable(name = "COURSES_FOR_FACULTY", joinColumns = { @JoinColumn(name = "CRS_CODE") }, inverseJoinColumns = { @JoinColumn(name = "FACULTY_ID") })
	private List<Faculty> faculties;

	// bi-directional many-to-one association to CoursesForStudent
	@OneToMany(mappedBy = "course")
	private List<CoursesForStudent> coursesForStudents;

	// bi-directional many-to-many association to CmrUser
	@ManyToMany
	@JoinTable(name = "COURSES_FOR_TEACHER", joinColumns = { @JoinColumn(name = "CRS_CODE") }, inverseJoinColumns = { @JoinColumn(name = "USR_ID") })
	private List<CmrUser> cmrUsers;

	public Course() {
	}

	public String getCrsCode() {
		return this.crsCode;
	}

	public void setCrsCode(String crsCode) {
		this.crsCode = crsCode;
	}

	public int getCredits() {
		return this.credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getCrsDetails() {
		return this.crsDetails;
	}

	public void setCrsDetails(String crsDetails) {
		this.crsDetails = crsDetails;
	}

	public String getCrsName() {
		return this.crsName;
	}

	public void setCrsName(String crsName) {
		this.crsName = crsName;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPreRequisite() {
		return this.preRequisite;
	}

	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Cmr> getCmrs() {
		return this.cmrs;
	}

	public void setCmrs(List<Cmr> cmrs) {
		this.cmrs = cmrs;
	}

	public Cmr addCmr(Cmr cmr) {
		getCmrs().add(cmr);
		cmr.setCourse(this);

		return cmr;
	}

	public Cmr removeCmr(Cmr cmr) {
		getCmrs().remove(cmr);
		cmr.setCourse(null);

		return cmr;
	}

	public CmrUser getCmrUser1() {
		return this.cmrUser1;
	}

	public void setCmrUser1(CmrUser cmrUser1) {
		this.cmrUser1 = cmrUser1;
	}

	public CmrUser getCmrUser2() {
		return this.cmrUser2;
	}

	public void setCmrUser2(CmrUser cmrUser2) {
		this.cmrUser2 = cmrUser2;
	}

	public List<Faculty> getFaculties() {
		return this.faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}

	public List<CoursesForStudent> getCoursesForStudents() {
		return this.coursesForStudents;
	}

	public void setCoursesForStudents(List<CoursesForStudent> coursesForStudents) {
		this.coursesForStudents = coursesForStudents;
	}

	public CoursesForStudent addCoursesForStudent(
			CoursesForStudent coursesForStudent) {
		getCoursesForStudents().add(coursesForStudent);
		coursesForStudent.setCourse(this);

		return coursesForStudent;
	}

	public CoursesForStudent removeCoursesForStudent(
			CoursesForStudent coursesForStudent) {
		getCoursesForStudents().remove(coursesForStudent);
		coursesForStudent.setCourse(null);

		return coursesForStudent;
	}

	public List<CmrUser> getCmrUsers() {
		return this.cmrUsers;
	}

	public void setCmrUsers(List<CmrUser> cmrUsers) {
		this.cmrUsers = cmrUsers;
	}
	
	public String getFullCourseLabel() {
		return crsCode + " - " + crsName;
	}
}
