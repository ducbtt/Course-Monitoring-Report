package com.ewsd.cmr.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ewsd.cmr.dao.CourseDao;
import com.ewsd.cmr.entity.Course;

@Repository
@SuppressWarnings("unchecked")
public class CourseDaoImpl extends AbstractCmrDao implements CourseDao {

	@Override
	public List<Course> getAllCourses() {
		return getSessionFactory().getCurrentSession().createCriteria(Course.class).list();
	}

	@Override
	public Course getCourseByCode(String courseCode) {
		return fetchById(courseCode, Course.class);
	}

	@Override
	public void deleteCourse(String courseCode) {
		delete(courseCode, Course.class);
	}

	@Override
	public void createCourse(Course course) {
		create(course);
	}

	@Override
	public void updateCourse(Course course) {
		update(course);
	}

	@Override
	public boolean isExistingNewCourse(String courseName) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("crsName", courseName));
		return ((Course) criteria.uniqueResult()) != null;
	}

	@Override
	public boolean isExistingCourseExcept(String courseCode, String courseName) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("crsName", courseName));
		criteria.add(Restrictions.ne("crsCode", courseCode));
		return ((Course) criteria.uniqueResult()) != null;
	}

	@Override
	public boolean isExistingNewCourseCode(String courseCode) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("crsCode", courseCode));
		return ((Course) criteria.uniqueResult()) != null;
	}

	@Override
	public List<Course> getAllCoursesByUser(int usrId) {
		Criteria criteria = session().createCriteria(Course.class, "course");
		criteria.createAlias("course.cmrUser1", "cl");
		criteria.createAlias("course.cmrUser2", "cm");
		criteria.add(Restrictions.or(Restrictions.eq("cl.usrId", usrId),
				Restrictions.eq("cm.usrId", usrId)));
		return criteria.list();
	}

}
