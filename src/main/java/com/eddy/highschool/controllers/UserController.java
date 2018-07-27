package com.eddy.highschool.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		User user = uS.findByUsername(username);
		model.addAttribute("currentUser", user);
		//Redirects to "/login" if noone in session
		if(user == null) return "redirect:/login";
		if(user.getRoles().get(0).getName().equals("ROLE_ADMIN")) {
			return "redirect:/admin";
		}else if(user.getRoles().get(0).getName().equals("ROLE_TEACHER")) {
			return "redirect:/teacher/homepage";
		}else if(user.getRoles().get(0).getName().equals("ROLE_STUDENT")) {
			return "redirect:/student/homepage";
		}else {
			return "noRoleErrorPage";	//Needed?
		}
	}
	
	//Main website's page, login page
	@RequestMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error, 
						@RequestParam(value="logout", required=false) String logout, 
						RedirectAttributes flash){
		System.out.println(error+"1");
		if(error != null) flash.addFlashAttribute("errorMessage", "Invalid Credentials, Please try again");
		if(logout != null) flash.addFlashAttribute("logoutMessage", "Logout Successful!");
		return "loginPage";
	}
}
