package com.ewsd.cmr.service.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ewsd.cmr.common.CmrStatusEnum;
import com.ewsd.cmr.common.HistoryActionTypeEnum;
import com.ewsd.cmr.common.RoleEnum;
import com.ewsd.cmr.dao.CmrDao;
import com.ewsd.cmr.dao.CmrHistoryDao;
import com.ewsd.cmr.dao.CmrStatusDao;
import com.ewsd.cmr.dao.CmrUserDao;
import com.ewsd.cmr.dao.CourseDao;
import com.ewsd.cmr.entity.Cmr;
import com.ewsd.cmr.entity.CmrHistory;
import com.ewsd.cmr.entity.CmrUser;
import com.ewsd.cmr.forms.CmrEditForm;
import com.ewsd.cmr.service.CmrService;
import com.ewsd.cmr.utils.DateUtils;

@Service
@Transactional
public class CmrServiceImpl implements CmrService {

	@Autowired
	private CmrDao cmrDao;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private CmrStatusDao statusDao;

	@Autowired
	private CmrUserDao userDao;

	@Autowired
	private CmrHistoryDao histDao;

	@Override
	public List<Cmr> getAllCmrsByUser(int userId) {
		CmrUser user = userDao.findUserById(userId);
		if (user == null) {
			return Collections.emptyList();
		}
		if (user.hasDLTRole()) {
			return cmrDao.getAllCmrsForRole(userId, RoleEnum.DLT);
		}
		if (user.hasPVCRole()) {
			return cmrDao.getAllCmrsForRole(userId, RoleEnum.PVC);
		}
		if (user.hasCMRole()) {
			return cmrDao.getAllCmrsForRole(userId, RoleEnum.CM);
		}
		if (user.hasCLRole()) {
			return cmrDao.getAllCmrsForRole(userId, RoleEnum.CL);
		}
		return Collections.emptyList();
	}

	@Override
	public Cmr findCmrById(int cmrId) {
		return cmrDao.findCmrById(cmrId);
	}

	@Override
	public Cmr saveCmr(int userId, CmrEditForm cmrForm) {
		if (cmrForm.getCmrId() == 0) {
			// create new
			Cmr cmr = new Cmr();
			updateCmrInfo(cmr, cmrForm);
			cmr.setCmrStatusBean(statusDao.findStatusByName(CmrStatusEnum.NEW.name()));
			final CmrUser creator = userDao.findUserById(userId);
			cmr.setCmrUser(creator);
			final Date now = Calendar.getInstance().getTime();
			cmr.setCreatedDate(now);
			cmrDao.createCmr(cmr);

			CmrHistory history = CmrHistory.createNewHistory(HistoryActionTypeEnum.NEW, "initial new cmr");
			history.setCreatedAt(now);
			history.setCmr(cmr);
			history.setCmrUser(creator);
			histDao.createHistory(history);
			return cmr;
		} else {
			// update
			Cmr cmr = cmrDao.findCmrById(cmrForm.getCmrId());
			if (cmr == null) {
				return cmr;
			}
			updateCmrInfo(cmr, cmrForm);
			final Date now = Calendar.getInstance().getTime();
			cmr.setModifiedDate(now);
			cmrDao.updateCmr(cmr);

			CmrHistory history = CmrHistory.createNewHistory(HistoryActionTypeEnum.UPDATE, "update the cmr");
			history.setCreatedAt(now);
			history.setCmr(cmr);
			history.setCmrUser(userDao.findUserById(userId));
			histDao.createHistory(history);
			return cmr;
		}
	}

	private void updateCmrInfo(Cmr cmr, CmrEditForm cmrForm) {
		cmr.setCourse(courseDao.getCourseByCode(cmrForm.getCrsCode()));
		cmr.setAllCredited(cmrForm.getAllCredited());
		cmr.setNoStdGreen(cmrForm.getNoStdGreen());
		cmr.setCrsSubmitDate(DateUtils.toDateAsPattern(cmrForm.getCrsSubmitDate(), DateUtils.DATETIME_MINUTE_PATTERN));
		cmr.setFbNoExcellent(cmrForm.getFbNoExcellent());
		cmr.setFbNoVeryGood(cmrForm.getFbNoVeryGood());
		cmr.setFbNoGood(cmrForm.getFbNoGood());
		cmr.setFbNoEnough(cmrForm.getFbNoEnough());
		cmr.setFbNoPoor(cmrForm.getFbNoPoor());
		cmr.setFbNoVeryPoor(cmrForm.getFbNoVeryPoor());
		cmr.setCmrNote(cmrForm.getCmrNote());
	}

	@Override
	public void deleteCmr(Cmr cmr) {
		// only delete the cmr with status is NEW, once the report is submitted
		// to CM,
		// it cannot be deleted anymore.
		if (!cmr.getCmrStatusBean().getStatusName().equalsIgnoreCase(CmrStatusEnum.NEW.name())) {
			return;
		}
		// delete all histories of the cmr
		histDao.deleteAllHistoriesForCmr(cmr.getCmrId());
		cmrDao.deleteCmr(cmr);
	}

	@Override
	public Cmr saveAndSendCmrToCm(int userId, CmrEditForm cmrForm) {
		Cmr cmr = saveCmr(userId, cmrForm);
		if (cmr == null) {
			return cmr;
		}
		cmr.setCmrStatusBean(statusDao.findStatusByName(CmrStatusEnum.PENDING_CM_APPROVE.name()));
		cmrDao.updateCmr(cmr);

		CmrHistory history = CmrHistory.createNewHistory(HistoryActionTypeEnum.UPDATE, "send cmr to CM");
		history.setCreatedAt(Calendar.getInstance().getTime());
		history.setCmr(cmr);
		history.setCmrUser(userDao.findUserById(userId));
		histDao.createHistory(history);
		return cmr;
	}

	@Override
	public void approveCmr(int usrId, CmrEditForm cmrForm) {
		CmrUser user = userDao.findUserById(usrId);
		if (user == null) {
			return;
		}
		if (!user.hasDLTRole() && !user.hasCMRole()) {
			// only role dlt or cm can approve the CMR
			return;
		}
		Cmr cmr = cmrDao.findCmrById(cmrForm.getCmrId());
		// update status
		CmrHistory history = null;
		if (user.hasDLTRole()) {
			cmr.setCmrStatusBean(statusDao.findStatusByName(CmrStatusEnum.COMPLETED.name()));
			history = CmrHistory.createNewHistory(HistoryActionTypeEnum.UPDATE, cmrForm.getComments());
		}
		if (user.hasCMRole()) {
			cmr.setCmrStatusBean(statusDao.findStatusByName(CmrStatusEnum.PENDING_DTL_RESPONSE.name()));
			history = CmrHistory.createNewHistory(HistoryActionTypeEnum.UPDATE, cmrForm.getComments());
		}
		cmrDao.updateCmr(cmr);
		// update history
		history.setCreatedAt(Calendar.getInstance().getTime());
		history.setCmr(cmr);
		history.setCmrUser(user);
		histDao.createHistory(history);
	}

}
