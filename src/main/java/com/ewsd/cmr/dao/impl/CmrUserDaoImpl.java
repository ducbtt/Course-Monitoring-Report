package com.ewsd.cmr.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ewsd.cmr.common.RoleEnum;
import com.ewsd.cmr.dao.CmrUserDao;
import com.ewsd.cmr.entity.CmrUser;

@Repository
@SuppressWarnings("unchecked")
public class CmrUserDaoImpl extends AbstractCmrDao implements CmrUserDao {

	public List<CmrUser> getAllUsers() {
		return fetchAll(CmrUser.class);
	}

	@Override
	public List<CmrUser> searchUserFromName(String searchName) {
		final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(CmrUser.class);
		criteria.add(Restrictions.like("usrName", searchName, MatchMode.ANYWHERE));
		return criteria.list();
	}

	@Override
	public void deleteUser(int id) {
		delete(id, CmrUser.class);
	}

	@Override
	public void createUser(CmrUser user) {
		create(user);
	}

	@Override
	public void updateUser(CmrUser user) {
		update(user);
	}

	@Override
	public CmrUser findUserByUsername(String username) {
		final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(CmrUser.class);
		criteria.add(Restrictions.eq("usrName", username));
		return (CmrUser) criteria.uniqueResult();
	}

	@Override
	public CmrUser findUserByEmail(String email) {
		final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(CmrUser.class);
		criteria.add(Restrictions.eq("email", email));
		return (CmrUser) criteria.uniqueResult();
	}

	@Override
	public CmrUser findUserById(int id) {
		return fetchById(id, CmrUser.class);
	}

	@Override
	public boolean isUsernameExistingExcept(int userId, String username) {
		final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(CmrUser.class);
		criteria.add(Restrictions.eq("usrName", username));
		criteria.add(Restrictions.ne("usrId", userId));
		return ((CmrUser) criteria.uniqueResult()) != null;
	}

	@Override
	public boolean isEmailExistingExcept(int userId, String email) {
		final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(CmrUser.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.ne("usrId", userId));
		return ((CmrUser) criteria.uniqueResult()) != null;
	}

	@Override
	public List<CmrUser> getAllDlts() {
		final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(CmrUser.class, "u");
		criteria.createAlias("u.cmrRoles", "r");
		criteria.add(Restrictions.eq("r.name", RoleEnum.DLT.name()));
		return criteria.list();
	}

	@Override
	public List<CmrUser> getAllPvcs() {
		final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(CmrUser.class, "u");
		criteria.createAlias("u.cmrRoles", "r");
		criteria.add(Restrictions.eq("r.name", RoleEnum.PVC.name()));
		return criteria.list();
	}

	@Override
	public List<CmrUser> getAllCls() {
		final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(CmrUser.class, "u");
		criteria.createAlias("u.cmrRoles", "r");
		criteria.add(Restrictions.eq("r.name", RoleEnum.CL.name()));
		return criteria.list();
	}

	@Override
	public List<CmrUser> getAllCms() {
		final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(CmrUser.class, "u");
		criteria.createAlias("u.cmrRoles", "r");
		criteria.add(Restrictions.eq("r.name", RoleEnum.CM.name()));
		return criteria.list();
	}

}