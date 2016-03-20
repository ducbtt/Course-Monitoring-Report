package com.ewsd.cmr.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the CMR_ROLE database table.
 * 
 * @author lequoctruong
 */
@Entity
@Table(name = "CMR_ROLE")
@NamedQuery(name = "CmrRole.findAll", query = "SELECT c FROM CmrRole c")
public class CmrRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID")
	private int roleId;

	private String description;

	private String name;

	// bi-directional many-to-many association to CmrUser
	@ManyToMany(mappedBy = "cmrRoles")
	private List<CmrUser> cmrUsers;

	public CmrRole() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public List<CmrUser> getCmrUsers() {
		return this.cmrUsers;
	}

	public void setCmrUsers(List<CmrUser> cmrUsers) {
		this.cmrUsers = cmrUsers;
	}

}