package com.ewsd.cmr.dao;

import java.util.List;

import com.ewsd.cmr.entity.CmrRole;

public interface CmrRoleDao {

	List<CmrRole> getAllRoles();

	CmrRole getRoleById(int id);

	void deleteRole(int id);

	void createRole(CmrRole role);

	void updateRole(CmrRole role);

	boolean isExistingNewRole(String roleName);

	boolean isExistingRoleExcept(int roleId, String roleName);

	List<CmrRole> findRolesIn(Integer[] roleIds);

}
