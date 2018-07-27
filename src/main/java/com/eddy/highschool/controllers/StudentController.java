package com.eddy.highschool.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eddy.highschool.models.Course;
import com.eddy.highschool.models.User;
import com.eddy.highschool.services.CourseStudentService;
import com.eddy.highschool.services.UserServices;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private UserServices uS;
	private CourseStudentService cSS;
	
	public StudentController(UserServices uS, CourseStudentService cSS) {
		this.uS = uS;
		this.cSS = cSS;
	}
	
	//Student Homepage shows enrolled courses, including final grade assigned
	@RequestMapping("/homepage")
	public String studentHomepage(Principal principal,
									Model model) {
		String username = principal.getName();
		User user = uS.findByUsername(username);
		model.addAttribute("currentUser",user);
		List<Course> courses = cSS.findCourses(user.getId());
		model.addAttribute("courses",courses);
		return "student/studentHomePage";
	}
}
