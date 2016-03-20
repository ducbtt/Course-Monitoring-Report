package com.ewsd.cmr.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ewsd.cmr.dao.CmrRoleDao;
import com.ewsd.cmr.entity.CmrRole;

@Repository
@SuppressWarnings("unchecked")
public class CmrRoleDaoImpl extends AbstractCmrDao implements CmrRoleDao {

	@Override
	public List<CmrRole> getAllRoles() {
		return getSessionFactory().getCurrentSession()
				.createCriteria(CmrRole.class).list();
	}

	@Override
	public CmrRole getRoleById(int id) {
		return fetchById(id, CmrRole.class);
	}

	@Override
	public void deleteRole(int id) {
		delete(id, CmrRole.class);
	}

	@Override
	public void createRole(CmrRole role) {
		create(role);
	}

	@Override
	public void updateRole(CmrRole role) {
		update(role);
	}

	@Override
	public boolean isExistingNewRole(String roleName) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(CmrRole.class);
		criteria.add(Restrictions.eq("name", roleName));
		return ((CmrRole) criteria.uniqueResult()) != null;
	}

	@Override
	public boolean isExistingRoleExcept(int roleId, String roleName) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(CmrRole.class);
		criteria.add(Restrictions.eq("name", roleName));
		criteria.add(Restrictions.ne("roleId", roleId));
		return ((CmrRole) criteria.uniqueResult()) != null;
	}

	
	@Override
	public List<CmrRole> findRolesIn(Integer[] roleIds) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(CmrRole.class);
		criteria.add(Restrictions.in("roleId", roleIds));
		return criteria.list();
	}

	
}
