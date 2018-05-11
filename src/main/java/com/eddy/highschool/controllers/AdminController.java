package com.eddy.highschool.controllers;

import java.security.Principal;
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
import com.eddy.highschool.validator.UserValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UserValidator uV;
	private UserServices uS;
	private CourseServices cS;
	
	public AdminController(UserServices uS, CourseServices cS, UserValidator uV) {
		this.uS = uS;
		this.cS = cS;
		this.uV = uV;
	}
	
	@RequestMapping("")
	public String adminHomepage(Principal principal,
								Model model) {
		String username = principal.getName();
		model.addAttribute("currentUser", uS.findByUsername(username));
		return "adminPage";
	}
	
	@RequestMapping("/user-registration")
	public String userRegisterForm(@Valid @ModelAttribute("user")User user) {
		return "userRegistrationPage";
	}
	
	@PostMapping("/user-registration")
	public String userRegistration(@Valid @ModelAttribute("user")User user, 
									BindingResult result, 
									RedirectAttributes flash, 
									@RequestParam("role")String role) {
		uV.validate(user,result);
		if(result.hasErrors()) {
			return "userRegistrationPage";
		}
		if(role.equals("student")){
			uS.saveWithStudentRole(user);
		}else if(role.equals("teacher")) {
			uS.saveWithTeacherRole(user);
		}else if(role.equals("admin")) {
			uS.saveWithAdminRole(user);
		}else {
			flash.addFlashAttribute("error", "You didnt choose a role");
			return "redirect:/admin/user-registration";
		}
		flash.addFlashAttribute("success", "Registration successful!");
		return "redirect:/admin";
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
	
	@RequestMapping("/students")
	public String showStudents(Model model) {
		model.addAttribute("allStudents", uS.findAllStudents());
		return "showStudents";
	}
	
	@RequestMapping("/teachers")
	public String showTeachers(Model model) {
		model.addAttribute("allTeachers", uS.findAllTeachers());
		return "showTeachers";
	}
	
	@RequestMapping("/students/{id}")
	public String seeCoursesAvailable(@PathVariable("id")Long id,
									Model model) {
		List<Course> allCourses = cS.allCourses();
		model.addAttribute("allCourses", allCourses);
		model.addAttribute("studentId",id);
		return "showCourses";
	}
	
	@PostMapping("/students")
	public String joinCourseStudent(@RequestParam("studentId")Long studentId,
									@RequestParam("courseId")Long courseId,
									RedirectAttributes flash) {
		User student = uS.findById(studentId);
		
		Course course = cS.findById(courseId);
		List<User> students = course.getStudents();
		
		for(User user: students) {
			if (studentId == user.getId()) {
				flash.addFlashAttribute("error", "Already signed to course");
				return "redirect:/admin/students/"+studentId;
			}
		}
		
		students.add(student);
		course.setStudents(students);
		cS.update(course);
		return "redirect:/admin/students";
	}
	//Logic to join courses and students
	/* 

	@PostMapping("/courses/{id}")
	public String addCourse(@PathVariable ("id")Long id, 
							Principal principal) {
		String username = principal.getName();
		User student = uS.findByUsername(username);
		
		Course course = cS.findById(id);
		List<User> students = course.getStudents();
		
		//Check if student is already enrolled in course
		//Not really needed since once a student is enrolled in a class, the add button is disabled
		for(User s: students) {
			if (s.getId() == student.getId()) {
				return "redirect:/student/homepage";
				//show error in flash
			}
		}
		
		students.add(student);
		System.out.println("Size of student's list: "+students.size());
		course.setStudents(students);
		cS.update(course);
		return "redirect:/student/homepage";
	}
	*/
}

