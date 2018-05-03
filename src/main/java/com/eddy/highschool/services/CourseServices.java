package com.eddy.highschool.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.eddy.highschool.models.Course;
import com.eddy.highschool.repositories.CourseRepository;

@Service
public class CourseServices {
	private CourseRepository cR;
	
	public CourseServices(CourseRepository cR) {
		this.cR = cR;
	}
	
	public ArrayList<Course> allCourses(){
		return (ArrayList<Course>) this.cR.findAll();
	}
	
	public void saveCourse(Course course) {
		this.cR.save(course);
	}
}
