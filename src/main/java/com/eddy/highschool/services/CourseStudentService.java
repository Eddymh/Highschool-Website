package com.eddy.highschool.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eddy.highschool.models.Course;
import com.eddy.highschool.models.CourseStudent;
import com.eddy.highschool.models.User;
import com.eddy.highschool.repositories.CourseStudentRepository;

@Service
public class CourseStudentService {

	private CourseStudentRepository cSR;
	public CourseStudentService(CourseStudentRepository cSR) {
		this.cSR = cSR;
	}
	
	public List<CourseStudent> findAll() {
		return  (List<CourseStudent>) this.cSR.findAll();
	}
	
	public void save(CourseStudent cs) {
		this.cSR.save(cs);
	}
	
	public List<User> findStudents(Long course_id) {
		List<CourseStudent> allStudents =  (List<CourseStudent>) cSR.findAll();
		List<User> students = new ArrayList<User>();;
		for(CourseStudent cs: allStudents) {
			if(cs.getCourse().getId() == course_id) {
				students.add(cs.getUser());
			}
		}
		return students;
	}
	
	public List<Long> findStudentsId(Long course_id) {
		List<CourseStudent> allStudents =  (List<CourseStudent>) cSR.findAll();
		List<Long> students = new ArrayList<Long>();;
		for(CourseStudent cs: allStudents) {
			if(cs.getCourse().getId() == course_id) {
				students.add(cs.getUser().getId());
			}
		}
		return students;
	}
	
	public List<Course> findCourses(Long student_id){
		List<CourseStudent> allCourses = (List<CourseStudent>) cSR.findAll();
		List<Course> courses = new ArrayList<Course>();
		for(CourseStudent cs: allCourses) {
			if(cs.getUser().getId() == student_id) {
				courses.add(cs.getCourse());
			}
		}
		return courses;
	}
	
}
