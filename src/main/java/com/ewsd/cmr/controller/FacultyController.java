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

import com.ewsd.cmr.entity.Faculty;
import com.ewsd.cmr.forms.FacultyEditForm;
import com.ewsd.cmr.service.CmrUserService;
import com.ewsd.cmr.service.FacultyService;

@Controller
public class FacultyController {

	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private CmrUserService userService;

	@RequestMapping("allFaculties")
	public ModelAndView getAllFaculties() {
		List<Faculty> allFaculties = facultyService.getAllFaculties();
		List<FacultyEditForm> facultyList = new ArrayList<>();
		allFaculties.stream().forEach(faculty -> {
			FacultyEditForm editForm = new FacultyEditForm(faculty);
			facultyList.add(editForm);
		} );
		return new ModelAndView("facultyList", "facultyList", facultyList);
	}

	@RequestMapping("editFaculty")
	public ModelAndView editFaculty(@RequestParam("id") int facultyId) {
		Faculty faculty = facultyService.getFacultyById(facultyId);
		FacultyEditForm facultyForm = new FacultyEditForm(faculty);
		facultyForm.setDlts(userService.getAllDlts());
		facultyForm.setPvcs(userService.getAllPvcs());
		facultyForm.setAction("update");
		return new ModelAndView("facultyForm", "facultyForm", facultyForm);
	}

	@RequestMapping("deleteFaculty")
	public ModelAndView deleteFaculty(@RequestParam("id") int facultyId) {
		facultyService.deleteFaculty(facultyId);
		return new ModelAndView("redirect:allFaculties");
	}

	@RequestMapping("saveFaculty")
	public ModelAndView saveFaculty(@ModelAttribute("facultyForm") FacultyEditForm facultyForm) {
		final String validationErrorMsg = validateEditForm(facultyForm);
		if (!StringUtils.isEmpty(validationErrorMsg)) {
			return new ModelAndView("facultyForm", "formError", validationErrorMsg);
		}
		if (facultyForm.isCreatedForm()) {
			facultyService.createFaculty(facultyForm);
		} else {
			facultyService.updateFaculty(facultyForm);
		}
		return new ModelAndView("redirect:allFaculties");
	}

	private String validateEditForm(FacultyEditForm facultyForm) {
		if (StringUtils.isEmpty(facultyForm.getName())) {
			return "Faculty Name must not be blank";
		}
		if (StringUtils.isEmpty(facultyForm.getDescription())) {
			return "Faculty Description must not be blank";
		}
		if (facultyService.isExistingFaculty(facultyForm.getFacultyId(), facultyForm.getName())) {
			return "Faculty Name already exists. Please choose another";
		}
		return StringUtils.EMPTY;
	}

	@RequestMapping(value = "createFaculty")
	public ModelAndView createFaculty() {
		FacultyEditForm facultyForm = new FacultyEditForm();
		facultyForm.setDlts(userService.getAllDlts());
		facultyForm.setPvcs(userService.getAllPvcs());
		facultyForm.setAction("create");
		return new ModelAndView("facultyForm", "facultyForm", facultyForm);
	}
}