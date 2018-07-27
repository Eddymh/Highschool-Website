package com.eddy.highschool.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eddy.highschool.models.Course;
import com.eddy.highschool.repositories.CourseRepository;

@Service
public class CourseServices {
	private CourseRepository cR;
	
	public CourseServices(CourseRepository cR) {
		this.cR = cR;
	}
	
	/*Returns a list with all courses created */
	public List<Course> allCourses(){
		return (List<Course>) this.cR.findAll();
	}
	
	public void saveCourse(Course course) {
		this.cR.save(course);
	}
	
	public Course findById(Long id) {
		return this.cR.findById(id).orElse(null);
	}
	
	public void update(Course course) {
		this.cR.save(course);
	}
	
	public void delete(Course course) {
		this.cR.delete(course);
	}
}
