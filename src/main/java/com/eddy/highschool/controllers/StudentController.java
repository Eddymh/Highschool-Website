package com.eddy.highschool.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eddy.highschool.models.Course;
import com.eddy.highschool.models.User;
import com.eddy.highschool.services.CourseServices;
import com.eddy.highschool.services.UserServices;
import com.eddy.highschool.validator.UserValidator;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private CourseServices cS;
	private UserServices uS;
	private UserValidator uV;
	
	public StudentController(UserServices uS, UserValidator uV, CourseServices cS) {
		this.uS = uS;
		this.uV = uV;
		this.cS = cS;
	}
	
	@RequestMapping("/homepage")
	public String studentHomepage(Principal principal,
									Model model) {
		String username = principal.getName();
		User user = uS.findByUsername(username);
		model.addAttribute("currentUser",user);
		
		List<Course> courses = user.getCoursesTaken();
		model.addAttribute("courses",courses);
		return "student/studentHomePage";
	}
	
}
