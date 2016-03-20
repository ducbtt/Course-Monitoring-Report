package com.ewsd.cmr.dao;

import java.util.List;

import com.ewsd.cmr.entity.Course;

public interface CourseDao {

	List<Course> getAllCourses();

	Course getCourseByCode(String courseCode);

	void deleteCourse(String courseCode);

	void createCourse(Course course);

	void updateCourse(Course course);

	boolean isExistingNewCourse(String courseName);

	boolean isExistingCourseExcept(String courseCode, String courseName);

	boolean isExistingNewCourseCode(String courseCode);
	
	List<Course> getAllCoursesByUser(int usrId);

}
