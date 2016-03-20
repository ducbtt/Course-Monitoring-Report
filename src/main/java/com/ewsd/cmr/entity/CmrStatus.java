package com.ewsd.cmr.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.ewsd.cmr.common.CmrStatusEnum;

import java.util.List;

/**
 * The persistent class for the CMR_STATUS database table.
 * 
 * @author lequoctruong
 */

@Entity
@Table(name = "CMR_STATUS")
@NamedQuery(name = "CmrStatus.findAll", query = "SELECT c FROM CmrStatus c")
public class CmrStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATUS_ID")
	private int statusId;

	@Column(name = "STATUS_DESC")
	private String statusDesc;

	@Column(name = "STATUS_NAME")
	private String statusName;

	// bi-directional many-to-one association to Cmr
	@OneToMany(mappedBy = "cmrStatusBean")
	private List<Cmr> cmrs;

	public CmrStatus() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<Cmr> getCmrs() {
		return this.cmrs;
	}

	public void setCmrs(List<Cmr> cmrs) {
		this.cmrs = cmrs;
	}

	public Cmr addCmr(Cmr cmr) {
		getCmrs().add(cmr);
		cmr.setCmrStatusBean(this);

		return cmr;
	}

	public Cmr removeCmr(Cmr cmr) {
		getCmrs().remove(cmr);
		cmr.setCmrStatusBean(null);

		return cmr;
	}
	
	public boolean isStatusNew() {
		return this.statusName.equalsIgnoreCase(CmrStatusEnum.NEW.name());
	}
	
	public boolean isPendingCM() {
		return this.statusName.equalsIgnoreCase(CmrStatusEnum.PENDING_CM_APPROVE.name());
	}
	
	public boolean isPendingDLT() {
		return this.statusName.equalsIgnoreCase(CmrStatusEnum.PENDING_DTL_RESPONSE.name());
	}
	
	public boolean isCompleted() {
		return this.statusName.equalsIgnoreCase(CmrStatusEnum.COMPLETED.name());
	}

}