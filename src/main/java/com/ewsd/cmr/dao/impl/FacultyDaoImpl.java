package com.ewsd.cmr.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ewsd.cmr.dao.FacultyDao;
import com.ewsd.cmr.entity.Faculty;

@Repository
@SuppressWarnings("unchecked")
public class FacultyDaoImpl extends AbstractCmrDao implements FacultyDao {

	
	@Override
	public List<Faculty> getAllFaculties() {
		return getSessionFactory().getCurrentSession().createCriteria(Faculty.class).list();
	}

	@Override
	public Faculty getFacultyById(int facultyId) {
		return fetchById(facultyId, Faculty.class);
	}

	@Override
	public void deleteFaculty(int facultyId) {
		delete(facultyId, Faculty.class);
	}

	@Override
	public void createFaculty(Faculty faculty) {
		create(faculty);
	}

	@Override
	public void updateFaculty(Faculty faculty) {
		update(faculty);
	}

	@Override
	public boolean isExistingNewFaculty(String facultyName) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Faculty.class);
		criteria.add(Restrictions.eq("name", facultyName));
		return ((Faculty) criteria.uniqueResult()) != null;
	}

	@Override
	public boolean isExistingFacultyExcept(int facultyId, String facultyName) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Faculty.class);
		criteria.add(Restrictions.eq("name", facultyName));
		criteria.add(Restrictions.ne("facultyId", facultyId));
		return ((Faculty) criteria.uniqueResult()) != null;
	}

	@Override
	public List<Faculty> findFacultiesIn(Integer[] facultyIds) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(Faculty.class);
		criteria.add(Restrictions.in("facultyId", facultyIds));
		return criteria.list();
	}
}
