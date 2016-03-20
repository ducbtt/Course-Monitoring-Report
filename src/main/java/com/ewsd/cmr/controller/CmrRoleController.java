package com.ewsd.cmr.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ewsd.cmr.entity.CmrRole;
import com.ewsd.cmr.forms.CmrRoleEditForm;
import com.ewsd.cmr.service.CmrRoleService;

@Controller
public class CmrRoleController {

	@Autowired
	private CmrRoleService roleService;

	@RequestMapping("allRoles")
	public ModelAndView getAllRoles() {
		List<CmrRole> allRoles = roleService.getAllRoles();
		List<CmrRoleEditForm> roleList = new ArrayList<>();
		allRoles.stream().forEach(role -> roleList.add(new CmrRoleEditForm(role)));
		return new ModelAndView("roleList", "roleList", roleList);
	}

	@RequestMapping("editRole")
	public ModelAndView editRole(@RequestParam int id) {
		CmrRole role = roleService.getRoleById(id);
		CmrRoleEditForm roleForm = new CmrRoleEditForm(role);
		roleForm.setAction("update");
		return new ModelAndView("roleForm", "roleForm", roleForm);
	}

	@RequestMapping("deleteRole")
	public ModelAndView deleteRole(@RequestParam int id) {
		roleService.deleteRole(id);
		return new ModelAndView("redirect:allRoles");
	}

	@RequestMapping("saveRole")
	public ModelAndView saveRole(@ModelAttribute("roleForm") CmrRoleEditForm roleForm) {
		final String validationErrorMsg = validateEditForm(roleForm);
		if (!StringUtils.isEmpty(validationErrorMsg)) {
			return new ModelAndView("roleForm", "formError", validationErrorMsg);
		}
		if (roleForm.isCreatedForm()) {
			roleService.createRole(roleForm);
		} else {
			roleService.updateRole(roleForm);
		}
		return new ModelAndView("redirect:allRoles");
	}

	private String validateEditForm(CmrRoleEditForm roleForm) {
		if (StringUtils.isEmpty(roleForm.getName())) {
			return "Role Name must not be blank";
		}
		if (StringUtils.isEmpty(roleForm.getName())) {
			return "Role Name must not be blank";
		}
		if (roleService.isExistingRole(roleForm.getRoleId(), roleForm.getName())) {
			return "Role Name already exists. Please choose another";
		}
		return StringUtils.EMPTY;
	}

	@RequestMapping(value = "createRole")
	public ModelAndView createUser() {
		CmrRoleEditForm roleForm = new CmrRoleEditForm();
		roleForm.setAction("create");
		return new ModelAndView("roleForm", "roleForm", roleForm);
	}
}