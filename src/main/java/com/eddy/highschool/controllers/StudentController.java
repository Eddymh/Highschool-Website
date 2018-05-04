package com.eddy.highschool.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eddy.highschool.models.User;
import com.eddy.highschool.services.UserServices;
import com.eddy.highschool.validator.UserValidator;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private UserServices uS;
	private UserValidator uV;
	
	public StudentController(UserServices uS, UserValidator uV) {
		this.uS = uS;
		this.uV = uV;
	}
	
	@RequestMapping("/registration")
	public String registerForm(@Valid @ModelAttribute("user")User user) {
		return "studentRegistrationPage";
	}
	
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user")User user, 
								BindingResult result, 
								RedirectAttributes flash) {
		
		uV.validate(user,result);
		
		if(result.hasErrors()) {
			return "studentRegistrationPage";
		}
		uS.saveWithStudentRole(user);
		return "redirect:/login";
	}
}
