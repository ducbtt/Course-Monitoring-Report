package com.ewsd.cmr.service;

import java.util.List;

import com.ewsd.cmr.entity.CmrRole;
import com.ewsd.cmr.forms.CmrRoleEditForm;

public interface CmrRoleService {

	List<CmrRole> getAllRoles();

	CmrRole getRoleById(int id);

	void deleteRole(int id);

	void createRole(CmrRoleEditForm roleForm);

	void updateRole(CmrRoleEditForm roleForm);

	boolean isExistingRole(int roleId, String name);

}
