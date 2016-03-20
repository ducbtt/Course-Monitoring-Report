package com.ewsd.cmr.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the CMR database table.
 * 
 * @author lequoctruong
 */

@Entity
@NamedQuery(name = "Cmr.findAll", query = "SELECT c FROM Cmr c")
public class Cmr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CMR_ID")
	private int cmrId;

	@Column(name = "ALL_CREDITED")
	private String allCredited;

	@Column(name = "CMR_NOTE")
	private String cmrNote;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRS_SUBMIT_DATE")
	private Date crsSubmitDate;

	@Column(name = "FB_NO_ENOUGH")
	private int fbNoEnough;

	@Column(name = "FB_NO_EXCELLENT")
	private int fbNoExcellent;

	@Column(name = "FB_NO_GOOD")
	private int fbNoGood;

	@Column(name = "FB_NO_POOR")
	private int fbNoPoor;

	@Column(name = "FB_NO_VERY_GOOD")
	private int fbNoVeryGood;

	@Column(name = "FB_NO_VERY_POOR")
	private int fbNoVeryPoor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "NO_STD_GREEN")
	private int noStdGreen;

	// bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name = "CRS_CODE")
	private Course course;

	// bi-directional many-to-one association to CmrStatus
	@ManyToOne
	@JoinColumn(name = "CMR_STATUS")
	private CmrStatus cmrStatusBean;

	// bi-directional many-to-one association to CmrUser
	@ManyToOne
	@JoinColumn(name = "CREATED_BY")
	private CmrUser cmrUser;

	// bi-directional many-to-one association to CmrHistory
	@OneToMany(mappedBy = "cmr")
	private List<CmrHistory> cmrHistories;

	public Cmr() {
	}

	public int getCmrId() {
		return this.cmrId;
	}

	public void setCmrId(int cmrId) {
		this.cmrId = cmrId;
	}

	public String getAllCredited() {
		return this.allCredited;
	}

	public void setAllCredited(String allCredited) {
		this.allCredited = allCredited;
	}

	public String getCmrNote() {
		return this.cmrNote;
	}

	public void setCmrNote(String cmrNote) {
		this.cmrNote = cmrNote;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getCrsSubmitDate() {
		return this.crsSubmitDate;
	}

	public void setCrsSubmitDate(Date crsSubmitDate) {
		this.crsSubmitDate = crsSubmitDate;
	}

	public int getFbNoEnough() {
		return this.fbNoEnough;
	}

	public void setFbNoEnough(int fbNoEnough) {
		this.fbNoEnough = fbNoEnough;
	}

	public int getFbNoExcellent() {
		return this.fbNoExcellent;
	}

	public void setFbNoExcellent(int fbNoExcellent) {
		this.fbNoExcellent = fbNoExcellent;
	}

	public int getFbNoGood() {
		return this.fbNoGood;
	}

	public void setFbNoGood(int fbNoGood) {
		this.fbNoGood = fbNoGood;
	}

	public int getFbNoPoor() {
		return this.fbNoPoor;
	}

	public void setFbNoPoor(int fbNoPoor) {
		this.fbNoPoor = fbNoPoor;
	}

	public int getFbNoVeryGood() {
		return this.fbNoVeryGood;
	}

	public void setFbNoVeryGood(int fbNoVeryGood) {
		this.fbNoVeryGood = fbNoVeryGood;
	}

	public int getFbNoVeryPoor() {
		return this.fbNoVeryPoor;
	}

	public void setFbNoVeryPoor(int fbNoVeryPoor) {
		this.fbNoVeryPoor = fbNoVeryPoor;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getNoStdGreen() {
		return this.noStdGreen;
	}

	public void setNoStdGreen(int noStdGreen) {
		this.noStdGreen = noStdGreen;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public CmrStatus getCmrStatusBean() {
		return this.cmrStatusBean;
	}

	public void setCmrStatusBean(CmrStatus cmrStatusBean) {
		this.cmrStatusBean = cmrStatusBean;
	}

	public CmrUser getCmrUser() {
		return this.cmrUser;
	}

	public void setCmrUser(CmrUser cmrUser) {
		this.cmrUser = cmrUser;
	}

	public List<CmrHistory> getCmrHistories() {
		return this.cmrHistories;
	}

	public void setCmrHistories(List<CmrHistory> cmrHistories) {
		this.cmrHistories = cmrHistories;
	}

	public CmrHistory addCmrHistory(CmrHistory cmrHistory) {
		getCmrHistories().add(cmrHistory);
		cmrHistory.setCmr(this);

		return cmrHistory;
	}

	public CmrHistory removeCmrHistory(CmrHistory cmrHistory) {
		getCmrHistories().remove(cmrHistory);
		cmrHistory.setCmr(null);

		return cmrHistory;
	}

}