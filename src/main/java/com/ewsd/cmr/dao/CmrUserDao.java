package com.ewsd.cmr.dao;

import java.util.List;

import com.ewsd.cmr.entity.CmrUser;

public interface CmrUserDao {

	List<CmrUser> getAllUsers();

	List<CmrUser> searchUserFromName(String searchName);

	void deleteUser(int id);

	void createUser(CmrUser user);

	void updateUser(CmrUser user);

	CmrUser findUserByUsername(String username);

	CmrUser findUserByEmail(String email);

	CmrUser findUserById(int id);

	boolean isUsernameExistingExcept(int userId, String username);

	boolean isEmailExistingExcept(int userId, String email);

	List<CmrUser> getAllDlts();

	List<CmrUser> getAllPvcs();

	List<CmrUser> getAllCls();

	List<CmrUser> getAllCms();
}
