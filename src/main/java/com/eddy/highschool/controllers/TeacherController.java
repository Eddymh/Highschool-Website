package com.eddy.highschool.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eddy.highschool.models.User;
import com.eddy.highschool.services.UserServices;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	private UserServices uS;
	public TeacherController(UserServices uS) {
		this.uS = uS;
	}
	
	@RequestMapping("/registration")
	public String registerForm(@Valid @ModelAttribute("user")User user) {
		return "teacherRegistrationPage";
	}
	
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user")User user, 
								BindingResult result, 
								RedirectAttributes flash) {
		if(result.hasErrors()) {
			return "teacherRegistrationPage";
		}
		//uS.saveWithAdminRole(user);
		uS.saveWithTeacherRole(user);
		return "redirect:/login";
	}
}
