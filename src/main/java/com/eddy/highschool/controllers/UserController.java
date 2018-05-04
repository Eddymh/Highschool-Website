package com.eddy.highschool.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eddy.highschool.models.User;
import com.eddy.highschool.services.UserServices;

@Controller
public class UserController {
	
	private UserServices uS;
	
	public UserController(UserServices uS) {
		this.uS = uS;
	}
	
	@RequestMapping("/")	
	public String home(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("currentUser", uS.findByUsername(username));
		User user = uS.findByUsername(username);
		
		//should go by default to /login if noone on session
		if(user == null) {
			return "redirect:/login";
		}
		
		if(user.getRoles().get(0).getName().equals("ROLE_ADMIN")) {
			return "redirect:/admin";
		}
		if(user.getRoles().get(0).getName().equals("ROLE_TEACHER")) {
			return "teacherPage";
		}
		if(user.getRoles().get(0).getName().equals("ROLE_STUDENT")) {
			return "studentPage";
		}
		return "noRoleErrorPage";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error, 
						@RequestParam(value="logout", required=false) String logout, 
						Model model){
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please try again");
		}
		if(logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}
		return "loginPage";
	}
	
	@RequestMapping("/admin")
	public String adminPage(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("currentUser", uS.findByUsername(username));
		return "adminPage";
	}
	
}
