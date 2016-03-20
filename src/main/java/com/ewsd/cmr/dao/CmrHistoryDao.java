package com.ewsd.cmr.dao;

import com.ewsd.cmr.entity.CmrHistory;

public interface CmrHistoryDao {

	void createHistory(CmrHistory history);

	void deleteAllHistoriesForCmr(int cmrId);

	String findLastComments(int userId, int cmrId);

}
