package com.ewsd.cmr.service;

import java.util.List;

import com.ewsd.cmr.entity.Faculty;
import com.ewsd.cmr.forms.FacultyEditForm;

public interface FacultyService {

	List<Faculty> getAllFaculties();

	Faculty getFacultyById(int facultyId);

	void deleteFaculty(int facultyId);
	
	void createFaculty(FacultyEditForm facultyForm);

	void updateFaculty(FacultyEditForm facultyForm);

	boolean isExistingFaculty(int facultyId, String facultyName);

}
