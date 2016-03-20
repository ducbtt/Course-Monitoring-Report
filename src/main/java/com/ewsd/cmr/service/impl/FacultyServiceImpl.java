package com.ewsd.cmr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ewsd.cmr.dao.CmrUserDao;
import com.ewsd.cmr.dao.FacultyDao;
import com.ewsd.cmr.entity.Faculty;
import com.ewsd.cmr.forms.FacultyEditForm;
import com.ewsd.cmr.service.FacultyService;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {

	@Autowired
	private FacultyDao facultyDao;

	@Autowired
	private CmrUserDao userDao;

	@Override
	public List<Faculty> getAllFaculties() {
		return facultyDao.getAllFaculties();
	}

	@Override
	public Faculty getFacultyById(int facultyId) {
		return facultyDao.getFacultyById(facultyId);
	}

	@Override
	public void deleteFaculty(int facultyId) {
		facultyDao.deleteFaculty(facultyId);
	}

	@Override
	public void createFaculty(FacultyEditForm facultyForm) {
		Faculty faculty = new Faculty();
		updateFacultyInfo(faculty, facultyForm);
		facultyDao.createFaculty(faculty);
	}

	private void updateFacultyInfo(Faculty faculty, FacultyEditForm facultyForm) {
		faculty.setName(facultyForm.getName());
		faculty.setDescription(facultyForm.getDescription());
		faculty.setCmrUser1(userDao.findUserById(facultyForm.getDlt()));
		faculty.setCmrUser2(userDao.findUserById(facultyForm.getPvc()));
	}

	@Override
	public void updateFaculty(FacultyEditForm facultyForm) {
		Faculty faculty = facultyDao.getFacultyById(facultyForm.getFacultyId());
		updateFacultyInfo(faculty, facultyForm);
		facultyDao.updateFaculty(faculty);
	}

	@Override
	public boolean isExistingFaculty(int facultyId, String facultyName) {
		if (facultyId == 0) {
			return facultyDao.isExistingNewFaculty(facultyName);
		}
		return facultyDao.isExistingFacultyExcept(facultyId, facultyName);
	}

}
