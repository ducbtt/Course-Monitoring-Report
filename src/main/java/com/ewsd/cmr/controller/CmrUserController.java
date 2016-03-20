package com.ewsd.cmr.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ewsd.cmr.entity.CmrRole;
import com.ewsd.cmr.entity.CmrUser;
import com.ewsd.cmr.entity.Faculty;
import com.ewsd.cmr.forms.BoolForm;
import com.ewsd.cmr.forms.CmrUserEditForm;
import com.ewsd.cmr.forms.LoginForm;
import com.ewsd.cmr.service.CmrRoleService;
import com.ewsd.cmr.service.CmrUserService;
import com.ewsd.cmr.service.FacultyService;

@Controller
public class CmrUserController {

	private static final Logger LOG = LoggerFactory.getLogger(CmrUserController.class);

	@Autowired
	private CmrUserService userService;

	@Autowired
	private CmrRoleService roleService;

	@Autowired
	private FacultyService facultyService;

	@RequestMapping(value = "/")
	public ModelAndView initLogin(HttpServletRequest request) {
		if (isValidatedUser(request.getSession())) { // already existing session
			return new ModelAndView("home");
		}
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/home")
	public ModelAndView home(HttpServletRequest request) {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("loginForm") LoginForm loginForm) {
		try {
			if (isValidatedUser(request.getSession())) {
				return new ModelAndView("home");
			}
			final CmrUser user = userService.authenticate(loginForm.getEmail(), loginForm.getPassword());
			request.getSession().setAttribute("sessionUser", user);
		} catch (CredentialNotFoundException e) {
			LOG.error("User cannot be found");
			return new ModelAndView("login", "error", "User cannot be found");
		} catch (CredentialException e) {
			LOG.error("Password does not match");
			return new ModelAndView("login", "error", "Password does not match");
		}
		return new ModelAndView("home");
	}

	private boolean isValidatedUser(HttpSession session) {
		return (CmrUser) session.getAttribute("sessionUser") != null;
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("login");
	}

	@RequestMapping("editUser")
	public ModelAndView editUser(@RequestParam int id) {
		CmrUser user = userService.getUser(id);
		CmrUserEditForm userForm = new CmrUserEditForm(user);
		Map<String, Object> model = createModelForForm(user, userForm, "update");

		return new ModelAndView("userForm", model);
	}

	private Map<String, Object> createModelForForm(CmrUser user, CmrUserEditForm userForm, final String action) {
		if (user != null) {
			userForm.setRoles(findUserRoleIds(user));
			userForm.setFaculties(findUserFacultyIds(user));
		}
		List<BoolForm> statusList = Arrays.asList(new BoolForm("Y", "Active"), new BoolForm("N", "Inactive"));
		Map<String, Object> model = new HashMap<>();
		if (!StringUtils.isEmpty(action)) {
			userForm.setAction(action);
		}
		model.put("userForm", userForm);
		model.put("statusList", statusList);
		List<CmrRole> roles = roleService.getAllRoles();
		model.put("roleList", roles);
		List<Faculty> faculties = facultyService.getAllFaculties();
		model.put("facultyList", faculties);
		return model;
	}

	private Integer[] findUserRoleIds(CmrUser user) {
		List<CmrRole> roles = user.getCmrRoles();
		if (CollectionUtils.isEmpty(roles)) {
			return null;
		}
		return roles.stream().map(role -> role.getRoleId()).collect(Collectors.toList()).toArray(new Integer[] {});
	}

	private Integer[] findUserFacultyIds(CmrUser user) {
		List<Faculty> faculties = user.getFaculties3();
		if (CollectionUtils.isEmpty(faculties)) {
			return null;
		}
		return faculties.stream().map(faculty -> faculty.getFacultyId()).collect(Collectors.toList())
				.toArray(new Integer[] {});
	}

	@RequestMapping(value = "saveUser")
	public ModelAndView saveUser(@ModelAttribute("userForm") CmrUserEditForm userForm) {
		final String validationErrorMsg = validateEditForm(userForm);
		if (!StringUtils.isEmpty(validationErrorMsg)) {
			Map<String, Object> model = createModelForForm(null, userForm, StringUtils.EMPTY);
			model.put("formError", validationErrorMsg);
			return new ModelAndView("userForm", model);
		}
		if (userForm.isCreatedForm()) {
			userService.createUser(userForm);
		} else {
			userService.updateUser(userForm);
		}
		return new ModelAndView("redirect:allUsers");
	}

	@RequestMapping(value = "createUser")
	public ModelAndView createUser() {
		CmrUserEditForm userForm = new CmrUserEditForm();
		Map<String, Object> model = createModelForForm(null, userForm, "create");

		return new ModelAndView("userForm", model);
	}

	private String validateEditForm(CmrUserEditForm userForm) {
		if (StringUtils.isEmpty(userForm.getUsrName())) {
			return "User Name must not be blank";
		}
		if (userService.isUsernameExisting(userForm.getUsrId(), userForm.getUsrName())) {
			return "User Name already exists. Please choose another";
		}
		if (StringUtils.isEmpty(userForm.getUsrFname())) {
			return "First Name must not be blank";
		}
		if (StringUtils.isEmpty(userForm.getUsrLname())) {
			return "Last Name must not be blank";
		}
		if (!StringUtils.isEmpty(userForm.getNewUsrPsw()) || !StringUtils.isEmpty(userForm.getConfirmNewUsrPsw())) {
			if (!StringUtils.equals(userForm.getNewUsrPsw(), userForm.getConfirmNewUsrPsw())) {
				return "Confirm New Password does not match with New Password";
			}
		}
		if (StringUtils.isEmpty(userForm.getDob())) {
			return "Date Of Birth must not be blank";
		}
		if (StringUtils.isEmpty(userForm.getTelephone())) {
			return "Telephone must not be blank";
		}
		if (StringUtils.isEmpty(userForm.getEmail())) {
			return "Email must not be blank";
		}
		if (userService.isEmailExisting(userForm.getUsrId(), userForm.getEmail())) {
			return "Email already exists. Please choose another";
		}
		return StringUtils.EMPTY;
	}

	@RequestMapping("deleteUser")
	public ModelAndView deleteUser(@RequestParam int id) {
		userService.deleteUser(id);
		return new ModelAndView("redirect:allUsers");
	}

	@RequestMapping("allUsers")
	public ModelAndView getAllUsers() {
		List<CmrUser> allUsers = userService.getAllUsers();
		List<CmrUserEditForm> userList = new ArrayList<>();
		allUsers.stream().forEach(user -> userList.add(new CmrUserEditForm(user)));
		return new ModelAndView("userList", "userList", userList);
	}

	@RequestMapping("searchUser")
	public ModelAndView searchUser(@RequestParam("searchName") String searchName) {
		List<CmrUser> userList = userService.searchUserFromName(searchName);
		return new ModelAndView("userList", "userList", userList);
	}

}