package com.ewsd.cmr.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ewsd.cmr.dao.CmrHistoryDao;
import com.ewsd.cmr.entity.CmrHistory;

@Repository
public class CmrHistoryDaoImpl extends AbstractCmrDao implements CmrHistoryDao {

	@Override
	public void createHistory(CmrHistory history) {
		create(history);
	}

	@Override
	public void deleteAllHistoriesForCmr(int cmrId) {
		Query query = session().createQuery("delete from CmrHistory h where h.cmr.cmrId = :cmrId");
		query.setParameter("cmrId", cmrId);
		query.executeUpdate();
	}

	@Override
	public String findLastComments(int userId, int cmrId) {
		Criteria criteria = session().createCriteria(CmrHistory.class, "hist");
		criteria.add(Restrictions.eq("hist.cmr.cmrId", cmrId));
		criteria.add(Restrictions.eq("hist.cmrUser.usrId", userId));
		criteria.addOrder(Order.desc("hist.createdAt"));
		criteria.setProjection(Projections.property("hist.histContent"));
		return (String) criteria.uniqueResult();
	}

}
