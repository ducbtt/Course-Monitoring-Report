package com.ewsd.cmr.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialNotFoundException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ewsd.cmr.dao.CmrRoleDao;
import com.ewsd.cmr.dao.CmrUserDao;
import com.ewsd.cmr.dao.FacultyDao;
import com.ewsd.cmr.entity.CmrUser;
import com.ewsd.cmr.forms.CmrUserEditForm;
import com.ewsd.cmr.service.CmrUserService;
import com.ewsd.cmr.utils.DateUtils;

@Service
@Transactional
public class CmrUserServiceImpl implements CmrUserService {

	@Autowired
	private CmrUserDao userDao;

	@Autowired
	private CmrRoleDao roleDao;

	@Autowired
	private FacultyDao facultyDao;

	@Override
	public List<CmrUser> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public List<CmrUser> searchUserFromName(String searchName) {
		return userDao.searchUserFromName(searchName);
	}

	@Override
	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

	@Override
	public CmrUser getUser(int id) {
		return userDao.findUserById(id);
	}

	@Override
	public CmrUser authenticate(String email, String password) throws CredentialNotFoundException, CredentialException {
		final CmrUser user = userDao.findUserByEmail(email);
		if (user == null) {
			throw new CredentialNotFoundException(email);
		}
		final String hashedPassword = DigestUtils.md5Hex(password);
		if (user.getUsrPsw().equals(hashedPassword)) {
			return user;
		}
		throw new CredentialException("password does not match!");
	}

	@Override
	public void createUser(CmrUserEditForm userForm) {
		CmrUser createdUser = new CmrUser();
		updateUserInfo(createdUser, userForm);
		createdUser.setCreatedDate(Calendar.getInstance().getTime());
		userDao.createUser(createdUser);
	}

	private void updateUserInfo(CmrUser user, CmrUserEditForm userForm) {
		user.setUsrName(userForm.getUsrName());
		user.setUsrFname(userForm.getUsrFname());
		user.setUsrLname(userForm.getUsrLname());
		if (!StringUtils.isEmpty(userForm.getNewUsrPsw())) {
			user.setUsrPsw(DigestUtils.md5Hex(userForm.getNewUsrPsw()));
		}
		user.setActive(userForm.getActive());
		user.setDob(DateUtils.toDate(userForm.getDob()));
		user.setEmail(userForm.getEmail());
		user.setTelephone(userForm.getTelephone());
		if (!ArrayUtils.isEmpty(userForm.getRoles())) {
			user.setCmrRoles(roleDao.findRolesIn(userForm.getRoles()));
		}
		if (!ArrayUtils.isEmpty(userForm.getFaculties())) {
			user.setFaculties3(facultyDao.findFacultiesIn(userForm.getFaculties()));
		}
	}

	@Override
	public void updateUser(CmrUserEditForm userForm) {
		CmrUser updatedUser = userDao.findUserById(userForm.getUsrId());
		updateUserInfo(updatedUser, userForm);
		updatedUser.setModifiedDate(Calendar.getInstance().getTime());

	}

	@Override
	public boolean isUsernameExisting(int userId, String username) {
		if (userId == 0) { // create
			return userDao.findUserByUsername(username) != null;
		} else {
			return userDao.isUsernameExistingExcept(userId, username);
		}
	}

	@Override
	public boolean isEmailExisting(int userId, String email) {
		if (userId == 0) { // create
			return userDao.findUserByEmail(email) != null;
		} else {
			return userDao.isEmailExistingExcept(userId, email);
		}
	}

	@Override
	public List<CmrUser> getAllDlts() {
		return userDao.getAllDlts();
	}

	@Override
	public List<CmrUser> getAllPvcs() {
		return userDao.getAllPvcs();
	}

	@Override
	public List<CmrUser> getAllCls() {
		return userDao.getAllCls();
	}

	@Override
	public List<CmrUser> getAllCms() {
		return userDao.getAllCms();
	}
}
