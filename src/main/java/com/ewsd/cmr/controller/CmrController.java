package com.ewsd.cmr.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ewsd.cmr.entity.Cmr;
import com.ewsd.cmr.entity.CmrUser;
import com.ewsd.cmr.entity.Course;
import com.ewsd.cmr.forms.BoolForm;
import com.ewsd.cmr.forms.CmrEditForm;
import com.ewsd.cmr.service.CmrHistoryService;
import com.ewsd.cmr.service.CmrService;
import com.ewsd.cmr.service.CourseService;
import com.ewsd.cmr.utils.DateUtils;

@Controller
public class CmrController {

	@Autowired
	CmrService cmrService;

	@Autowired
	CourseService courseService;
	
	@Autowired
	CmrHistoryService histService;

	@RequestMapping(value = "allCmrs", method = RequestMethod.GET)
	public ModelAndView allCmrs(HttpServletRequest request, @RequestParam("usrId") int usrId) {
		List<Cmr> cmrs = cmrService.getAllCmrsByUser(usrId);
		List<CmrEditForm> cmrList = new ArrayList<>();
		CmrUser user = (CmrUser) request.getSession().getAttribute("sessionUser");
		cmrs.stream().forEach(cmr -> {
			CmrEditForm editForm = new CmrEditForm(cmr);
			editForm.setShowed(checkIfCmrCanBeShowed(user, cmr));
			cmrList.add(editForm);
		});
		return new ModelAndView("cmrList", "cmrList", cmrList);
	}

	private boolean checkIfCmrCanBeShowed(CmrUser user, Cmr cmr) {
		if (user.hasCLRole()) {
			return true;
		}
		if (user.hasCMRole() && !cmr.getCmrStatusBean().isStatusNew()) {
			return true;
		}
		if ((user.hasDLTRole() || user.hasPVCRole()) && !cmr.getCmrStatusBean().isStatusNew()
				&& !cmr.getCmrStatusBean().isPendingCM()) {
			return true;
		}
		return false;
	}

	@RequestMapping("viewCmr")
	public ModelAndView viewCmr(HttpServletRequest request, @RequestParam("cmrId") int cmrId) {
		Cmr cmr = cmrService.findCmrById(cmrId);
		CmrEditForm cmrForm = new CmrEditForm(cmr);
		CmrUser user = (CmrUser) request.getSession().getAttribute("sessionUser");
		cmrForm.setComments(histService.findLastComments(user.getUsrId(), cmrId));
		cmrForm.setEnableForApprove(checkIfCanBeApproved(user, cmr));

		Map<String, Object> model = new HashMap<>();
		cmrForm.setAction("approve");
		model.put("cmrForm", cmrForm);

		List<BoolForm> allCreditedList = Arrays.asList(new BoolForm("Y", "Yes"), new BoolForm("N", "No"));
		model.put("allCreditedList", allCreditedList);

		return new ModelAndView("cmrViewForm", model);
	}

	private boolean checkIfCanBeApproved(CmrUser user, Cmr cmr) {
		
		if (user.hasCMRole() && !cmr.getCmrStatusBean().isPendingDLT() && !cmr.getCmrStatusBean().isCompleted()) {
			return true;
		}
		if (user.hasDLTRole() && !cmr.getCmrStatusBean().isCompleted()) {
			return true;
		}
		return false;
	}

	@RequestMapping("editCmr")
	public ModelAndView editCmr(HttpServletRequest request, @RequestParam("cmrId") int cmrId) {
		Cmr cmr = cmrService.findCmrById(cmrId);
		CmrEditForm cmrForm = new CmrEditForm(cmr);

		Map<String, Object> model = new HashMap<>();
		cmrForm.setAction("update");
		model.put("cmrForm", cmrForm);

		List<BoolForm> allCreditedList = Arrays.asList(new BoolForm("Y", "Yes"), new BoolForm("N", "No"));
		model.put("allCreditedList", allCreditedList);

		CmrUser user = (CmrUser) request.getSession().getAttribute("sessionUser");
		List<Course> allMyCourses = courseService.getAllCoursesByUser(user.getUsrId());
		model.put("courseList", allMyCourses);

		return new ModelAndView("cmrEditForm", model);
	}

	@RequestMapping(value = "createCourseReport")
	public ModelAndView createCourseReport(HttpServletRequest request, @RequestParam("courseCode") String courseCode) {
		CmrEditForm cmrForm = new CmrEditForm();
		cmrForm.setCourse(courseService.getCourseByCode(courseCode));

		Map<String, Object> model = new HashMap<>();
		cmrForm.setAction("create");

		CmrUser user = (CmrUser) request.getSession().getAttribute("sessionUser");
		List<Course> allMyCourses = courseService.getAllCoursesByUser(user.getUsrId());
		model.put("courseList", allMyCourses);
		cmrForm.setCrsCode(courseCode);
		cmrForm.setCrsDetails(courseService.getCourseByCode(courseCode).getCrsDetails());

		model.put("cmrForm", cmrForm);
		cmrForm.setAllCredited("Y"); // default

		List<BoolForm> allCreditedList = Arrays.asList(new BoolForm("Y", "Yes"), new BoolForm("N", "No"));
		model.put("allCreditedList", allCreditedList);
		return new ModelAndView("cmrEditForm", model);
	}

	@RequestMapping(value = "saveCmr", params = { "saveonly" })
	public ModelAndView saveCmr(HttpServletRequest request, @ModelAttribute("cmrForm") CmrEditForm cmrForm,
			BindingResult results, ModelMap model) {
		final String validationErrorMsg = validateEditForm(cmrForm, results);
		CmrUser user = (CmrUser) request.getSession().getAttribute("sessionUser");
		if (!StringUtils.isEmpty(validationErrorMsg)) {
			List<Course> allMyCourses = courseService.getAllCoursesByUser(user.getUsrId());
			model.put("courseList", allMyCourses);
			model.put("formError", validationErrorMsg);
			List<BoolForm> allCreditedList = Arrays.asList(new BoolForm("Y", "Yes"), new BoolForm("N", "No"));
			model.put("allCreditedList", allCreditedList);
			return new ModelAndView("cmrEditForm", model);
		}
		cmrService.saveCmr(user.getUsrId(), cmrForm);
		return new ModelAndView("redirect:allCmrs?usrId=" + user.getUsrId());
	}

	private String validateEditForm(CmrEditForm cmrForm, BindingResult results) {
		if (results.hasErrors()) {
			if (results.getFieldError("noStdGreen") != null) {
				return "No of Allowed Students must be numeric";
			}
			// feedbacks must be integer
			if (results.getFieldError("fbNoExcellent") != null) {
				return "Feedback Excellent must be numeric";
			}
			// no of allowed students must be integer
			if (results.getFieldError("fbNoVeryGood") != null) {
				return "Feedback Very Good must be numeric";
			}
			if (results.getFieldError("fbNoGood") != null) {
				return "Feedback Good must be numeric";
			}
			if (results.getFieldError("fbNoEnough") != null) {
				return "Feedback Enough must be numeric";
			}
			if (results.getFieldError("fbNoPoor") != null) {
				return "Feedback Poor must be numeric";
			}
			if (results.getFieldError("fbNoVeryPoor") != null) {
				return "Feedback Very Poor must be numeric";
			}
		}
		// course must not be null
		if (StringUtils.isEmpty(cmrForm.getCrsCode())) {
			return "Course code must not be blank";
		}
		// date should be in format dd.mm.yyyy hh:mm
		if (!StringUtils.isEmpty(cmrForm.getCrsSubmitDate())
				&& !DateUtils.isValidDate(cmrForm.getCrsSubmitDate(), "dd.MM.yyyy HH:mm")) {
			return "Final Course Submit Date is not a valid date, format: dd.MM.yyyy HH:mm";
		}
		return StringUtils.EMPTY;
	}

	@RequestMapping("deleteCmr")
	public ModelAndView deleteCmr(HttpServletRequest request, @RequestParam("cmrId") int cmrId) {
		CmrUser user = (CmrUser) request.getSession().getAttribute("sessionUser");
		Objects.requireNonNull(user, "invalid user session");
		Cmr cmr = cmrService.findCmrById(cmrId);
		Objects.requireNonNull(cmr, "invalid cmrId");

		cmrService.deleteCmr(cmr);
		return new ModelAndView("redirect:allCmrs?usrId=" + user.getUsrId());
	}

	@RequestMapping(value = "saveCmr", params = { "saveandsend" })
	public ModelAndView saveAndSendCmrToCm(HttpServletRequest request, @ModelAttribute("cmrForm") CmrEditForm cmrForm,
			BindingResult results, ModelMap model, @RequestParam("saveandsend") String saveandsend) {
		final String validationErrorMsg = validateEditForm(cmrForm, results);
		final CmrUser user = (CmrUser) request.getSession().getAttribute("sessionUser");
		if (!StringUtils.isEmpty(validationErrorMsg)) {
			List<Course> allMyCourses = courseService.getAllCoursesByUser(user.getUsrId());
			model.put("courseList", allMyCourses);
			model.put("formError", validationErrorMsg);
			List<BoolForm> allCreditedList = Arrays.asList(new BoolForm("Y", "Yes"), new BoolForm("N", "No"));
			model.put("allCreditedList", allCreditedList);
			return new ModelAndView("cmrEditForm", model);
		}
		cmrService.saveAndSendCmrToCm(user.getUsrId(), cmrForm);
		return new ModelAndView("redirect:allCmrs?usrId=" + user.getUsrId());
	}

	@RequestMapping(value = "approveCmr", method = RequestMethod.POST)
	public ModelAndView approveCmr(HttpServletRequest request, @ModelAttribute("cmrForm") CmrEditForm cmrForm,
			BindingResult results, ModelMap model) {
		final CmrUser user = (CmrUser) request.getSession().getAttribute("sessionUser");
		cmrService.approveCmr(user.getUsrId(), cmrForm);
		return new ModelAndView("redirect:allCmrs?usrId=" + user.getUsrId());
	}
}
