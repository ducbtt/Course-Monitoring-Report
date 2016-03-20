package com.ewsd.cmr.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the COURSES_FOR_STUDENT database table.
 * 
 * @author lequoctruong
 */

@Entity
@Table(name = "COURSES_FOR_STUDENT")
@NamedQuery(name = "CoursesForStudent.findAll", query = "SELECT c FROM CoursesForStudent c")
public class CoursesForStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CoursesForStudentPK id;

	@Column(name = "MRK_EXTRA")
	private double mrkExtra;

	@Column(name = "MRK_FINAL")
	private double mrkFinal;

	@Column(name = "MRK_PRACTICE")
	private double mrkPractice;

	@Column(name = "MRK_THEORY")
	private double mrkTheory;

	@Column(name = "NO_ATTENDED")
	private int noAttended;

	// bi-directional many-to-one association to CmrUser
	@ManyToOne
	@JoinColumn(name = "USR_ID")
	private CmrUser cmrUser;

	// bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name = "CRS_CODE")
	private Course course;

	public CoursesForStudent() {
	}

	public CoursesForStudentPK getId() {
		return this.id;
	}

	public void setId(CoursesForStudentPK id) {
		this.id = id;
	}

	public double getMrkExtra() {
		return this.mrkExtra;
	}

	public void setMrkExtra(double mrkExtra) {
		this.mrkExtra = mrkExtra;
	}

	public double getMrkFinal() {
		return this.mrkFinal;
	}

	public void setMrkFinal(double mrkFinal) {
		this.mrkFinal = mrkFinal;
	}

	public double getMrkPractice() {
		return this.mrkPractice;
	}

	public void setMrkPractice(double mrkPractice) {
		this.mrkPractice = mrkPractice;
	}

	public double getMrkTheory() {
		return this.mrkTheory;
	}

	public void setMrkTheory(double mrkTheory) {
		this.mrkTheory = mrkTheory;
	}

	public int getNoAttended() {
		return this.noAttended;
	}

	public void setNoAttended(int noAttended) {
		this.noAttended = noAttended;
	}

	public CmrUser getCmrUser() {
		return this.cmrUser;
	}

	public void setCmrUser(CmrUser cmrUser) {
		this.cmrUser = cmrUser;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}