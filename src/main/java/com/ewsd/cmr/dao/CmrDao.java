package com.ewsd.cmr.dao;

import java.util.List;

import com.ewsd.cmr.common.RoleEnum;
import com.ewsd.cmr.entity.Cmr;

public interface CmrDao {

	List<Cmr> getAllCmrsByUser(final int userId);

	Cmr findCmrById(final int cmrId);

	void createCmr(Cmr cmr);

	void updateCmr(Cmr cmr);

	void deleteCmr(Cmr cmr);

	List<Cmr> getAllCmrsForRole(int userId, RoleEnum role);
	
}
