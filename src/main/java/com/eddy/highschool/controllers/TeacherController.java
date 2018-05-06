package com.eddy.highschool.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eddy.highschool.models.Course;
import com.eddy.highschool.models.User;
import com.eddy.highschool.services.UserServices;
import com.eddy.highschool.validator.UserValidator;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	private UserServices uS;
	private UserValidator uV;
	
	public TeacherController(UserServices uS, UserValidator uV) {
		this.uS = uS;
		this.uV = uV;
	}
	
	@RequestMapping("/homepage")
	public String teacherHomePage(Principal principal, 
									Model model) {
		String username = principal.getName();
		User user = uS.findByUsername(username);
		List<Course> courses = user.getCourses();
		model.addAttribute("currentUser",user);
		model.addAttribute("courses",courses);
		return "teacherPage";
	}
	
	@RequestMapping("/registration")
	public String registerForm(@Valid @ModelAttribute("user")User user) {
		return "teacherRegistrationPage";
	}
	
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user")User user, 
								BindingResult result, 
								RedirectAttributes flash) {
		
		uV.validate(user,result);
		
		if(result.hasErrors()) {
			return "teacherRegistrationPage";
		}
		//uS.saveWithAdminRole(user);
		uS.saveWithTeacherRole(user);
		return "redirect:/login";
	}
	
}
