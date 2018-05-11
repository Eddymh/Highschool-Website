package com.eddy.highschool.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eddy.highschool.models.Course;
import com.eddy.highschool.models.User;
import com.eddy.highschool.services.CourseServices;
import com.eddy.highschool.services.UserServices;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	private UserServices uS;
	private CourseServices cS;
	
	public TeacherController(UserServices uS, CourseServices cS) {
		this.uS = uS;
		this.cS = cS;
	}
	
	@RequestMapping("/homepage")
	public String teacherHomePage(Principal principal, 
									Model model) {
		String username = principal.getName();
		User user = uS.findByUsername(username);
		List<Course> courses = user.getCourses();
		model.addAttribute("currentUser",user);
		model.addAttribute("courses",courses);
		return "teacher/teacherHomePage";
	}
	
	@RequestMapping("/homepage/{id}")
	public String studentsEnrolled(@PathVariable ("id") Long id, 
									Model model) {
		Course course = cS.findById(id);
		List<User> students = course.getStudents();
		model.addAttribute("students", students);
		model.addAttribute("course", course);
		return "teacher/studentsEnrolled";
	}
	/*
	@PostMapping("/assignGrade")
	public String assignGrade(@RequestParam("studentId")Long studentId,
							@RequestParam("courseId")Long courseId,
							@RequestParam("finalGrade")Long grade) {
		User student = uS.findById(studentId);
		
		
		
		return "redirect:/teacher/homepage/" + courseId;
	}
	*/
}
