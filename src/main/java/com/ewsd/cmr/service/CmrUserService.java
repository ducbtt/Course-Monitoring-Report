
package com.ewsd.cmr.service;

import java.util.List;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialNotFoundException;

import com.ewsd.cmr.entity.CmrUser;
import com.ewsd.cmr.forms.CmrUserEditForm;

public interface CmrUserService {

	List<CmrUser> getAllUsers();

	List<CmrUser> searchUserFromName(String searchName);

	void deleteUser(int id);

	CmrUser getUser(int id);

	void createUser(CmrUserEditForm user);

	void updateUser(CmrUserEditForm user);

	CmrUser authenticate(String email, String password) throws CredentialNotFoundException, CredentialException;

	boolean isUsernameExisting(int userId, String username);

	boolean isEmailExisting(int userId, String email);

	List<CmrUser> getAllDlts();

	List<CmrUser> getAllPvcs();

	List<CmrUser> getAllCls();

	List<CmrUser> getAllCms();

}
