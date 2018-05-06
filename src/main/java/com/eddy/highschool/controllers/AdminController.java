package com.eddy.highschool.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		cS.saveCourse(course);
		return "redirect:/admin/courses";
	}
	
	@RequestMapping("/courses/{id}")
	public String updateCoursePage(@PathVariable ("id")Long id, 
								Model model) {
		model.addAttribute("course", cS.findById(id));
		return "adminCoursesUpdate";
	}
	
	@PostMapping("/courses/{id}")
	public String updateCourse(@PathVariable("id")Long id, 
								@RequestParam("name")String name, 
								@RequestParam("prefix")String prefix, 
								@RequestParam("description")String description, 
								@RequestParam("capacity")Long capacity, 
								RedirectAttributes flash) {
		Boolean errorsFound = false;
		if(name.length()<1) {
			flash.addAttribute("errors","Name field can't be empty");
			errorsFound=true;
		}
		if(prefix.length()<1) {
			flash.addAttribute("errors","Prefix field can't be empty");
			errorsFound=true;
		}
		if(description.length()<1) {
			flash.addAttribute("errors","Description field can't be empty");
			errorsFound=true;
		}
		if(capacity.longValue()<8) {
			flash.addAttribute("errors","Capacity shouldn't be smaller than 8");
			errorsFound=true;
		}
		if(errorsFound==true) {
			return "redirect:/admin/courses/{id}";
		}
		Course course = cS.findById(id);
		course.setName(name);
		course.setPrefix(prefix);
		course.setDescription(description);
		course.setCapacity(capacity);
		cS.update(course);
		return "redirect:/admin/courses";
	}
	
	@PostMapping("/courses/delete/{id}")
	public String deleteCourse(@PathVariable("id")Long id) {
		cS.delete(cS.findById(id));
		return "redirect:/admin/courses";
	}
	
}

