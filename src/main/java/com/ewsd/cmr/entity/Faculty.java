package com.ewsd.cmr.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the FACULTY database table.
 * 
 * @author lequoctruong
 */

@Entity
@NamedQuery(name = "Faculty.findAll", query = "SELECT f FROM Faculty f")
public class Faculty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FACULTY_ID")
	private int facultyId;

	private String description;

	private String name;

	// bi-directional many-to-many association to Course
	@ManyToMany(mappedBy = "faculties")
	private List<Course> courses;

	// bi-directional many-to-one association to CmrUser
	@ManyToOne
	@JoinColumn(name = "DLT_ID")
	private CmrUser cmrUser1;

	// bi-directional many-to-one association to CmrUser
	@ManyToOne
	@JoinColumn(name = "PVC_ID")
	private CmrUser cmrUser2;

	// bi-directional many-to-many association to CmrUser
	@ManyToMany(mappedBy = "faculties3")
	private List<CmrUser> cmrUsers;

	public Faculty() {
	}

	public int getFacultyId() {
		return this.facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
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

	public List<CmrUser> getCmrUsers() {
		return this.cmrUsers;
	}

	public void setCmrUsers(List<CmrUser> cmrUsers) {
		this.cmrUsers = cmrUsers;
	}

}