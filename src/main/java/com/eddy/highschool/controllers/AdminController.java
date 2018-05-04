package com.eddy.highschool.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eddy.highschool.models.Course;
import com.eddy.highschool.models.User;
import com.eddy.highschool.services.CourseServices;
import com.eddy.highschool.services.UserServices;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UserServices uS;
	private CourseServices cS;
	
	public AdminController(UserServices uS, CourseServices cS) {
		this.uS = uS;
		this.cS = cS;
	}
	
	@RequestMapping("/")
	public String adminHomepage() {
		return "adminPage";
	}
	
	@RequestMapping("/courses")
	public String coursesTable(@ModelAttribute("course")Course course, 
								Model model) {
		ArrayList<Course> allCourses = cS.allCourses();
		model.addAttribute("allCourses", allCourses);
		
		//List<Object[]> allTeachers = uS.findAllTeachers();
		List<User> allTeachers = uS.findAllTeachers();
		model.addAttribute("allTeachers", allTeachers);
		
		return "adminCourses";
	}
	
	@PostMapping("/courses")
	public String createCourses(@Valid @ModelAttribute("course")Course course, 
								BindingResult result) {
		if(result.hasErrors()) {
			return "adminCourses";
		}
		System.out.println("test");
		cS.saveCourse(course);
		return "redirect:/admin/courses";
	}
	/*
	@RequestMapping("/assignTeacher")
	public String showTeachers(@ModelAttribute("teacher")User teacher,
												Model model) {
		ArrayList<User> 
	}
	*/
}




