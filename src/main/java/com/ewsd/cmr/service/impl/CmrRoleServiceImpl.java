package com.ewsd.cmr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ewsd.cmr.dao.CmrRoleDao;
import com.ewsd.cmr.entity.CmrRole;
import com.ewsd.cmr.forms.CmrRoleEditForm;
import com.ewsd.cmr.service.CmrRoleService;

@Service
@Transactional
public class CmrRoleServiceImpl implements CmrRoleService {

	@Autowired
	private CmrRoleDao roleDao;

	@Override
	public List<CmrRole> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	public CmrRole getRoleById(int id) {
		return roleDao.getRoleById(id);
	}

	@Override
	public void deleteRole(int id) {
		roleDao.deleteRole(id);
	}

	@Override
	public void createRole(CmrRoleEditForm roleForm) {
		CmrRole role = new CmrRole();
		role.setName(roleForm.getName());
		role.setDescription(roleForm.getDescription());
		roleDao.createRole(role);
	}

	@Override
	public void updateRole(CmrRoleEditForm roleForm) {
		CmrRole role = roleDao.getRoleById(roleForm.getRoleId());
		role.setName(roleForm.getName());
		role.setDescription(roleForm.getDescription());
		roleDao.updateRole(role);
	}

	@Override
	public boolean isExistingRole(int roleId, String roleName) {
		if(roleId == 0) { // create role
			return roleDao.isExistingNewRole(roleName);
		}
		return roleDao.isExistingRoleExcept(roleId, roleName);
	}

}
