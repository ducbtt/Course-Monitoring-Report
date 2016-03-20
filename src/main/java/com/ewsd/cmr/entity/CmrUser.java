package com.ewsd.cmr.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.ewsd.cmr.common.RoleEnum;

/**
 * The persistent class for the CMR_USER database table.
 * 
 * @author lequoctruong
 */

@Entity
@Table(name = "CMR_USER")
@NamedQuery(name = "CmrUser.findAll", query = "SELECT c FROM CmrUser c")
public class CmrUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USR_ID")
	private int usrId;

	private String active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private Date dob;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "TELEPHONE")
	private String telephone;

	@Column(name = "USR_FNAME")
	private String usrFname;

	@Column(name = "USR_LNAME")
	private String usrLname;

	@Column(name = "USR_NAME")
	private String usrName;

	@Column(name = "USR_PSW")
	private String usrPsw;

	// bi-directional many-to-one association to Cmr
	@OneToMany(mappedBy = "cmrUser")
	private List<Cmr> cmrs;

	// bi-directional many-to-one association to CmrHistory
	@OneToMany(mappedBy = "cmrUser")
	private List<CmrHistory> cmrHistories;

	// bi-directional many-to-one association to Course
	@OneToMany(mappedBy = "cmrUser1")
	private List<Course> courses1;

	// bi-directional many-to-one association to Course
	@OneToMany(mappedBy = "cmrUser2")
	private List<Course> courses2;

	// bi-directional many-to-one association to CoursesForStudent
	@OneToMany(mappedBy = "cmrUser")
	private List<CoursesForStudent> coursesForStudents;

	// bi-directional many-to-many association to Course
	@ManyToMany(mappedBy = "cmrUsers")
	private List<Course> courses3;

	// bi-directional many-to-one association to Faculty
	@OneToMany(mappedBy = "cmrUser1")
	private List<Faculty> faculties1;

	// bi-directional many-to-one association to Faculty
	@OneToMany(mappedBy = "cmrUser2")
	private List<Faculty> faculties2;

	// bi-directional many-to-many association to CmrRole
	@ManyToMany
	@JoinTable(name = "ROLES_FOR_USER", joinColumns = { @JoinColumn(name = "USR_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private List<CmrRole> cmrRoles;

	// bi-directional many-to-many association to Faculty
	@ManyToMany
	@JoinTable(name = "USERS_FOR_FACULTY", joinColumns = { @JoinColumn(name = "USR_ID") }, inverseJoinColumns = { @JoinColumn(name = "FACULTY_ID") })
	private List<Faculty> faculties3;

	public CmrUser() {
	}

	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsrFname() {
		return this.usrFname;
	}

	public void setUsrFname(String usrFname) {
		this.usrFname = usrFname;
	}

	public String getUsrLname() {
		return this.usrLname;
	}

	public void setUsrLname(String usrLname) {
		this.usrLname = usrLname;
	}

	public String getUsrName() {
		return this.usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrPsw() {
		return this.usrPsw;
	}

	public void setUsrPsw(String usrPsw) {
		this.usrPsw = usrPsw;
	}

	public List<Cmr> getCmrs() {
		return this.cmrs;
	}

	public void setCmrs(List<Cmr> cmrs) {
		this.cmrs = cmrs;
	}

	public Cmr addCmr(Cmr cmr) {
		getCmrs().add(cmr);
		cmr.setCmrUser(this);

		return cmr;
	}

	public Cmr removeCmr(Cmr cmr) {
		getCmrs().remove(cmr);
		cmr.setCmrUser(null);

		return cmr;
	}

	public List<CmrHistory> getCmrHistories() {
		return this.cmrHistories;
	}

	public void setCmrHistories(List<CmrHistory> cmrHistories) {
		this.cmrHistories = cmrHistories;
	}

	public CmrHistory addCmrHistory(CmrHistory cmrHistory) {
		getCmrHistories().add(cmrHistory);
		cmrHistory.setCmrUser(this);

		return cmrHistory;
	}

	public CmrHistory removeCmrHistory(CmrHistory cmrHistory) {
		getCmrHistories().remove(cmrHistory);
		cmrHistory.setCmrUser(null);

		return cmrHistory;
	}

	public List<Course> getCourses1() {
		return this.courses1;
	}

	public void setCourses1(List<Course> courses1) {
		this.courses1 = courses1;
	}

	public Course addCourses1(Course courses1) {
		getCourses1().add(courses1);
		courses1.setCmrUser1(this);

		return courses1;
	}

	public Course removeCourses1(Course courses1) {
		getCourses1().remove(courses1);
		courses1.setCmrUser1(null);

		return courses1;
	}

	public List<Course> getCourses2() {
		return this.courses2;
	}

	public void setCourses2(List<Course> courses2) {
		this.courses2 = courses2;
	}

	public Course addCourses2(Course courses2) {
		getCourses2().add(courses2);
		courses2.setCmrUser2(this);

		return courses2;
	}

	public Course removeCourses2(Course courses2) {
		getCourses2().remove(courses2);
		courses2.setCmrUser2(null);

		return courses2;
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
		coursesForStudent.setCmrUser(this);

		return coursesForStudent;
	}

	public CoursesForStudent removeCoursesForStudent(
			CoursesForStudent coursesForStudent) {
		getCoursesForStudents().remove(coursesForStudent);
		coursesForStudent.setCmrUser(null);

		return coursesForStudent;
	}

	public List<Course> getCourses3() {
		return this.courses3;
	}

	public void setCourses3(List<Course> courses3) {
		this.courses3 = courses3;
	}

	public List<Faculty> getFaculties1() {
		return this.faculties1;
	}

	public void setFaculties1(List<Faculty> faculties1) {
		this.faculties1 = faculties1;
	}

	public Faculty addFaculties1(Faculty faculties1) {
		getFaculties1().add(faculties1);
		faculties1.setCmrUser1(this);

		return faculties1;
	}

	public Faculty removeFaculties1(Faculty faculties1) {
		getFaculties1().remove(faculties1);
		faculties1.setCmrUser1(null);

		return faculties1;
	}

	public List<Faculty> getFaculties2() {
		return this.faculties2;
	}

	public void setFaculties2(List<Faculty> faculties2) {
		this.faculties2 = faculties2;
	}

	public Faculty addFaculties2(Faculty faculties2) {
		getFaculties2().add(faculties2);
		faculties2.setCmrUser2(this);

		return faculties2;
	}

	public Faculty removeFaculties2(Faculty faculties2) {
		getFaculties2().remove(faculties2);
		faculties2.setCmrUser2(null);

		return faculties2;
	}

	public List<CmrRole> getCmrRoles() {
		return this.cmrRoles;
	}

	public void setCmrRoles(List<CmrRole> cmrRoles) {
		this.cmrRoles = cmrRoles;
	}

	public List<Faculty> getFaculties3() {
		return this.faculties3;
	}

	public void setFaculties3(List<Faculty> faculties3) {
		this.faculties3 = faculties3;
	}

	public boolean hasAdminRole() {
		return cmrRoles.stream().anyMatch(role -> StringUtils.equalsIgnoreCase(role.getName(), RoleEnum.ADMIN.name()));
	}

	public boolean hasCLRole() {
		return cmrRoles.stream().anyMatch(role -> StringUtils.equalsIgnoreCase(role.getName(), RoleEnum.CL.name()));
	}

	public boolean hasCMRole() {
		return cmrRoles.stream().anyMatch(role -> StringUtils.equalsIgnoreCase(role.getName(), RoleEnum.CM.name()));
	}

	public boolean hasPVCRole() {
		return cmrRoles.stream().anyMatch(role -> StringUtils.equalsIgnoreCase(role.getName(), RoleEnum.PVC.name()));
	}

	public boolean hasDLTRole() {
		return cmrRoles.stream().anyMatch(role -> StringUtils.equalsIgnoreCase(role.getName(), RoleEnum.DLT.name()));
	}

	public boolean hasGuestRole() {
		return cmrRoles.stream().anyMatch(role -> StringUtils.equalsIgnoreCase(role.getName(), RoleEnum.GUEST.name()));
	}

}