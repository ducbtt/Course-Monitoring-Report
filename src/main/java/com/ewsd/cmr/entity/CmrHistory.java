package com.ewsd.cmr.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ewsd.cmr.common.HistoryActionTypeEnum;

/**
 * The persistent class for the CMR_HISTORY database table.
 * 
 * @author lequoctruong
 */

@Entity
@Table(name = "CMR_HISTORY")
@NamedQuery(name = "CmrHistory.findAll", query = "SELECT c FROM CmrHistory c")
public class CmrHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HIST_ID")
	private int histId;

	@Column(name = "ACTION_TYPE")
	private String actionType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@Column(name = "HIST_CONTENT")
	private String histContent;

	// bi-directional many-to-one association to Cmr
	@ManyToOne
	@JoinColumn(name = "CMR_ID")
	private Cmr cmr;

	// bi-directional many-to-one association to CmrUser
	@ManyToOne
	@JoinColumn(name = "MODIFIED_BY")
	private CmrUser cmrUser;

	public CmrHistory() {
	}

	public int getHistId() {
		return this.histId;
	}

	public void setHistId(int histId) {
		this.histId = histId;
	}

	public String getActionType() {
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getHistContent() {
		return this.histContent;
	}

	public void setHistContent(String histContent) {
		this.histContent = histContent;
	}

	public Cmr getCmr() {
		return this.cmr;
	}

	public void setCmr(Cmr cmr) {
		this.cmr = cmr;
	}

	public CmrUser getCmrUser() {
		return this.cmrUser;
	}

	public void setCmrUser(CmrUser cmrUser) {
		this.cmrUser = cmrUser;
	}

	public static CmrHistory createNewHistory(HistoryActionTypeEnum action, final String histContent) {
		CmrHistory hist = new CmrHistory();
		hist.setActionType(action.name());
		hist.setHistContent(histContent);
		return hist;
	}

}