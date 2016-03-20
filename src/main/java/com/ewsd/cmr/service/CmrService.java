package com.ewsd.cmr.service;

import java.util.List;

import com.ewsd.cmr.entity.Cmr;
import com.ewsd.cmr.forms.CmrEditForm;

public interface CmrService {

	List<Cmr> getAllCmrsByUser(final int userId);

	Cmr findCmrById(final int cmrId);

	Cmr saveCmr(int userId, CmrEditForm cmrForm);

	void deleteCmr(Cmr cmr);

	Cmr saveAndSendCmrToCm(int userId, CmrEditForm cmrForm);

	void approveCmr(int usrId, CmrEditForm cmrForm);
}
