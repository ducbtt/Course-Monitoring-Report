package com.ewsd.cmr.service;

import java.util.List;

import com.ewsd.cmr.entity.Course;
import com.ewsd.cmr.forms.CourseEditForm;

public interface CourseService {

	List<Course> getAllCourses();

	Course getCourseByCode(String courseCode);

	void deleteCourse(String courseCode);
	
	void createCourse(CourseEditForm courseForm);

	void updateCourse(CourseEditForm courseForm);

	boolean isExistingCourseName(boolean isCreatedForm, String courseCode, String name);

	boolean isExistingCourseCode(String courseCode);

	List<Course> getAllCoursesByUser(int usrId);

}
