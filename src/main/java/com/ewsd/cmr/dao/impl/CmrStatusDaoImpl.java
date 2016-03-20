package com.ewsd.cmr.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ewsd.cmr.dao.CmrStatusDao;
import com.ewsd.cmr.entity.CmrStatus;

@Repository
public class CmrStatusDaoImpl extends AbstractCmrDao implements CmrStatusDao {

	@Override
	public CmrStatus findStatusByName(String statusName) {
		Criteria criteria = session().createCriteria(CmrStatus.class, "st");
		criteria.add(Restrictions.eq("st.statusName", statusName));
		return (CmrStatus) criteria.uniqueResult();
	}

}
