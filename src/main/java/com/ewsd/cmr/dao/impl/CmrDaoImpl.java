package com.ewsd.cmr.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ewsd.cmr.common.RoleEnum;
import com.ewsd.cmr.dao.CmrDao;
import com.ewsd.cmr.entity.Cmr;

@Repository
@SuppressWarnings("unchecked")
public class CmrDaoImpl extends AbstractCmrDao implements CmrDao {

	@Override
	public List<Cmr> getAllCmrsByUser(int userId) {
		Criteria criteria = session().createCriteria(Cmr.class, "cmr");
		criteria.createAlias("cmr.course", "course");
		criteria.createAlias("cmr.course.cmrUser1", "cl");
		criteria.createAlias("cmr.course.cmrUser2", "cm");
		criteria.add(Restrictions.or(Restrictions.eq("cl.usrId", userId), Restrictions.eq("cm.usrId", userId)));
		return criteria.list();
	}

	@Override
	public Cmr findCmrById(int cmrId) {
		return fetchById(cmrId, Cmr.class);
	}

	@Override
	public void createCmr(Cmr cmr) {
		create(cmr);
	}

	@Override
	public void updateCmr(Cmr cmr) {
		update(cmr);
	}

	@Override
	public void deleteCmr(Cmr cmr) {
		delete(cmr);
	}

	@Override
	public List<Cmr> getAllCmrsForRole(int userId, RoleEnum role) {
		Criteria criteria = session().createCriteria(Cmr.class, "cmr");
		if (role == RoleEnum.DLT) {
			criteria.createAlias("cmr.course", "course");
			criteria.createAlias("course.faculties", "faculty");
			criteria.createAlias("faculty.cmrUser1", "dlt");
			criteria.add(Restrictions.eq("dlt.usrId", userId));
		}
		else if (role == RoleEnum.PVC) {
			criteria.createAlias("cmr.course", "course");
			criteria.createAlias("course.faculties", "faculty");
			criteria.createAlias("faculty.cmrUser2", "pvc");
			criteria.add(Restrictions.eq("pvc.usrId", userId));
		} 
		else if (role == RoleEnum.CM) {
			criteria.createAlias("cmr.course", "course");
			criteria.createAlias("cmr.course.cmrUser2", "cm");
			criteria.add(Restrictions.eq("cm.usrId", userId));
		}
		else if (role == RoleEnum.CL) {
			criteria.createAlias("cmr.course", "course");
			criteria.createAlias("cmr.course.cmrUser1", "cl");
			criteria.add(Restrictions.eq("cl.usrId", userId));
		}
		return criteria.list();
	}

}
