package com.ewsd.cmr.dao;

import java.util.List;

import com.ewsd.cmr.entity.Faculty;

public interface FacultyDao {

	List<Faculty> getAllFaculties();

	Faculty getFacultyById(int facultyId);

	void deleteFaculty(int facultyId);

	void createFaculty(Faculty faculty);

	void updateFaculty(Faculty faculty);

	boolean isExistingNewFaculty(String facultyName);

	boolean isExistingFacultyExcept(int facultyId, String facultyName);

	List<Faculty> findFacultiesIn(Integer[] facultyIds);
}
