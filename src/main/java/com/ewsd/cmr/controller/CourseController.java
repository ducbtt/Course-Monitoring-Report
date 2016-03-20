package com.ewsd.cmr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ewsd.cmr.entity.Course;
import com.ewsd.cmr.entity.Faculty;
import com.ewsd.cmr.forms.CourseEditForm;
import com.ewsd.cmr.service.CmrUserService;
import com.ewsd.cmr.service.CourseService;
import com.ewsd.cmr.service.FacultyService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CmrUserService userService;

	@Autowired
	private FacultyService facultyService;

	@RequestMapping("allCourses")
	public ModelAndView getAllCourses() {
		List<Course> allCourses = courseService.getAllCourses();
		List<CourseEditForm> courseList = new ArrayList<>();
		allCourses.stream().forEach(
				course -> courseList.add(new CourseEditForm(course)));
		return new ModelAndView("courseList", "courseList", courseList);
	}

	@RequestMapping("editCourse")
	public ModelAndView editCourse(@RequestParam("courseCode") String courseCode) {
		Course course = courseService.getCourseByCode(courseCode);
		CourseEditForm courseForm = new CourseEditForm(course);

		Map<String, Object> model = new HashMap<>();
		courseForm.setCls(userService.getAllCls());
		courseForm.setCms(userService.getAllCms());
		courseForm.setFacultyIds(findCourseFacultyIds(course));
		courseForm.setAction("update");
		model.put("courseForm", courseForm);
		List<Faculty> faculties = facultyService.getAllFaculties();
		model.put("facultyList", faculties);

		return new ModelAndView("courseForm", model);
	}

	private Integer[] findCourseFacultyIds(Course course) {
		List<Faculty> faculties = course.getFaculties();
		if (CollectionUtils.isEmpty(faculties)) {
			return null;
		}
		return faculties.stream().map(faculty -> faculty.getFacultyId())
				.collect(Collectors.toList()).toArray(new Integer[] {});
	}

	@RequestMapping("deleteCourse")
	public ModelAndView deleteCourse(
			@RequestParam("courseCode") String courseCode) {
		courseService.deleteCourse(courseCode);
		return new ModelAndView("redirect:allCourses");
	}

	@RequestMapping("saveCourse")
	public ModelAndView saveCourse(
			@ModelAttribute("courseForm") CourseEditForm courseForm) {
		final String validationErrorMsg = validateEditForm(courseForm);
		if (!StringUtils.isEmpty(validationErrorMsg)) {
			return new ModelAndView("courseForm", "formError",
					validationErrorMsg);
		}
		if (courseForm.isCreatedForm()) {
			courseService.createCourse(courseForm);
		} else {
			courseService.updateCourse(courseForm);
		}
		return new ModelAndView("redirect:allCourses");
	}

	private String validateEditForm(CourseEditForm courseForm) {
		if (StringUtils.isEmpty(courseForm.getCrsCode())) {
			return "Course Code must not be blank";
		}
		if (courseForm.isCreatedForm()
				&& courseService.isExistingCourseCode(courseForm.getCrsCode())) {
			return "Course Code already exists. Please choose another";
		}
		if (StringUtils.isEmpty(courseForm.getCrsName())) {
			return "Course Name must not be blank";
		}
		if (courseService.isExistingCourseName(courseForm.isCreatedForm(),
				courseForm.getCrsCode(), courseForm.getCrsName())) {
			return "Course Name already exists. Please choose another";
		}
		return StringUtils.EMPTY;
	}

	@RequestMapping(value = "createCourse")
	public ModelAndView createCourse() {
		CourseEditForm courseForm = new CourseEditForm();

		Map<String, Object> model = new HashMap<>();
		courseForm.setCls(userService.getAllCls());
		courseForm.setCms(userService.getAllCms());
		courseForm.setAction("create");
		model.put("courseForm", courseForm);
		List<Faculty> faculties = facultyService.getAllFaculties();
		model.put("facultyList", faculties);

		return new ModelAndView("courseForm", model);
	}
	
	@RequestMapping(value = "allMyCourses", method=RequestMethod.GET)
	public ModelAndView allMyCourses(@RequestParam("usrId") int usrId) {
		List<Course> allMyCourses = courseService.getAllCoursesByUser(usrId);
		return new ModelAndView("myCourses", "courseList", allMyCourses);
	}
	
	@RequestMapping(value = "getCourseDesc", method = RequestMethod.POST)
    public @ResponseBody
    String getCourseDesc(@RequestParam("crsCode") String courseCode) {
		Course course = courseService.getCourseByCode(courseCode);
		if (course == null) {
			return "not found course: " + courseCode;
		}
        return course.getCrsDetails();
    }
	
	@RequestMapping("viewCourse")
	public ModelAndView viewCourse(@RequestParam("courseCode") String courseCode) {
		Course course = courseService.getCourseByCode(courseCode);
		CourseEditForm courseForm = new CourseEditForm(course);

		Map<String, Object> model = new HashMap<>();
		courseForm.setCls(userService.getAllCls());
		courseForm.setCms(userService.getAllCms());
		courseForm.setFacultyIds(findCourseFacultyIds(course));
		courseForm.setAction("view");
		model.put("courseForm", courseForm);
		List<Faculty> faculties = facultyService.getAllFaculties();
		model.put("facultyList", faculties);

		return new ModelAndView("courseViewForm", model);
	}
}