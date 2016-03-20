package com.ewsd.cmr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ewsd.cmr.dao.CmrHistoryDao;
import com.ewsd.cmr.service.CmrHistoryService;

@Service
@Transactional
public class CmrHistoryServiceImpl implements CmrHistoryService {

	@Autowired
	CmrHistoryDao histDao;
	
	@Override
	public String findLastComments(int userId, int cmrId) {
		return histDao.findLastComments(userId, cmrId);
	}

	
}
