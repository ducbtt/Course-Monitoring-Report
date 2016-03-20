package com.ewsd.cmr.service.impl;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ewsd.cmr.dao.CmrUserDao;
import com.ewsd.cmr.dao.CourseDao;
import com.ewsd.cmr.dao.FacultyDao;
import com.ewsd.cmr.entity.Course;
import com.ewsd.cmr.forms.CourseEditForm;
import com.ewsd.cmr.service.CourseService;
import com.ewsd.cmr.utils.DateUtils;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private CmrUserDao userDao;

	@Autowired
	private FacultyDao facultyDao;

	@Override
	public List<Course> getAllCourses() {
		return courseDao.getAllCourses();
	}

	@Override
	public Course getCourseByCode(String courseCode) {
		return courseDao.getCourseByCode(courseCode);
	}

	@Override
	public void deleteCourse(String courseCode) {
		courseDao.deleteCourse(courseCode);
	}

	@Override
	public void createCourse(CourseEditForm courseForm) {
		Course course = new Course();
		updateCourseInfo(course, courseForm);
		courseDao.createCourse(course);
	}

	@Override
	public void updateCourse(CourseEditForm courseForm) {
		Course course = courseDao.getCourseByCode(courseForm.getCrsCode());
		updateCourseInfo(course, courseForm);
		courseDao.updateCourse(course);
	}

	private void updateCourseInfo(Course course, CourseEditForm courseForm) {
		course.setCrsCode(courseForm.getCrsCode());
		course.setCrsName(courseForm.getCrsName());
		course.setCrsDetails(courseForm.getCrsDetails());
		course.setCredits(courseForm.getCredits());
		course.setPreRequisite(courseForm.getPreRequisite());
		course.setStartDate(DateUtils.toDate(courseForm.getStartDate()));
		course.setEndDate(DateUtils.toDate(courseForm.getEndDate()));
		if (!ArrayUtils.isEmpty(courseForm.getFacultyIds())) {
			course.setFaculties(facultyDao.findFacultiesIn(courseForm
					.getFacultyIds()));
		} else {
			course.setFaculties(null);
		}
		if (courseForm.getClId() != null) {
			course.setCmrUser1(userDao.findUserById(courseForm.getClId()));
		} else {
			course.setCmrUser1(null);
		}
		if (courseForm.getCmId() != null) {
			course.setCmrUser2(userDao.findUserById(courseForm.getCmId()));
		} else {
			course.setCmrUser2(null);
		}
	}

	@Override
	public boolean isExistingCourseName(boolean isCreatedForm,
			String courseCode, String courseName) {
		if (isCreatedForm) {
			return courseDao.isExistingNewCourse(courseName);
		}
		return courseDao.isExistingCourseExcept(courseCode, courseName);
	}

	@Override
	public boolean isExistingCourseCode(String courseCode) {
		return courseDao.isExistingNewCourseCode(courseCode);
	}

	@Override
	public List<Course> getAllCoursesByUser(int usrId) {
		return courseDao.getAllCoursesByUser(usrId);
	}

}
