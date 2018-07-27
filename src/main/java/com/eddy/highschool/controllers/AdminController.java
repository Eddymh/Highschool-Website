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
import com.eddy.highschool.models.CourseStudent;
import com.eddy.highschool.models.User;
import com.eddy.highschool.services.CourseServices;
import com.eddy.highschool.services.CourseStudentService;
import com.eddy.highschool.services.UserServices;
import com.eddy.highschool.validator.UserValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UserValidator uV;
	private UserServices uS;
	private CourseServices cS;
	private CourseStudentService cSS;
	
	//Dependencies are injected in the parameters of the constructor (IoC)
	public AdminController(UserServices uS, CourseServices cS, UserValidator uV, CourseStudentService cSS) {
		this.uS = uS;
		this.cS = cS;
		this.uV = uV;
		this.cSS = cSS;
	}
	
	// Admin Homepage
	@RequestMapping("")
	public String adminHomepage(Principal principal,
								Model model) {
		String username = principal.getName();
		model.addAttribute("currentUser", username);
		return "adminPage";
	}
	
	// Student/Teacher/Admin registration page
	@RequestMapping("/user-registration")
	public String userRegisterForm(@Valid @ModelAttribute("user")User user) {
		return "userRegistrationPage";
	}
	
	/*
	 * new User information is validated. If any of the inputs dont meet the requirements, the userRegistrationPage is reloaded
	 * If all requirements are met, admin is redirected to their Homepage, and a message is shown stating the registration was succesful
	 */
	@PostMapping("/user-registration")
	public String userRegistration(@Valid @ModelAttribute("user")User user, 
									BindingResult result, 
									RedirectAttributes flash, 
									@RequestParam("role")String role) {
		uV.validate(user,result);
		if(result.hasErrors()) return "userRegistrationPage";
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
	
	/*
	 * Course creation Page and List of courses already created
	 * Multiple links to update or delete a coruse. Drop students enrolled in specific courses.
	 */
	@RequestMapping("/courses")
	public String coursesTable(@ModelAttribute("course")Course course, 
								Model model) {
		List<Course> allCourses = cS.allCourses();
		model.addAttribute("allCourses", allCourses);
		List<User> allTeachers = uS.findAllTeachers();
		model.addAttribute("allTeachers", allTeachers);
		return "adminCourses";
	}
	
	/*
	 * If inputs for course creation are invalid, reload the page
	 * If inputs are valid, create course and redirect to Courses page
	 */
	@PostMapping("/courses")
	public String createCourses(@Valid @ModelAttribute("course")Course course, 
								BindingResult result,
								RedirectAttributes flash) {
		if(result.hasErrors()) { 
			flash.addFlashAttribute("errors", "Course not created, please review your inputs");			//Needs better form of validation, errors should show under failed inputs
			return "redirect:/admin/courses";
		}
		cS.saveCourse(course);
		return "redirect:/admin/courses";
	}
	
	/*
	 * Shows students enrolled in specific course. If there are no students enrolled, show message stating so
	 * 
	 */
	@RequestMapping("/courses/enrolled/{id}")
	public String updateEnrolledStudents(@PathVariable ("id")Long id,
											RedirectAttributes flash,
											Model model) {
		//get list of IDs from students enrolled in specific course
		List<User> enrolledStudents = cSS.findStudents(id);
		if(enrolledStudents.size() == 0) {
			flash.addFlashAttribute("errors", "No students enrolled in the selected course");
			return "redirect:/admin/courses";
		}
		model.addAttribute("course",cS.findById(id));
		model.addAttribute("students",enrolledStudents);
		return "studentsEnrolledByCourse";
	}
	
	//Drops specific student from the course
	@RequestMapping("/courses/drop/{course_id}/{student_id}")
	public String dropFromCourse(@PathVariable("course_id")Long course_id,
									@PathVariable("student_id")Long student_id) {
		cSS.dropStudentFromCourse(student_id, course_id);
		return "redirect:/admin/courses/enrolled/" + course_id;
	}
	
	//Page where course data can be updated
	@RequestMapping("/courses/{id}")
	public String updateCoursePage(@PathVariable ("id")Long id, 
								Model model) {
		model.addAttribute("course", cS.findById(id));
		return "adminCoursesUpdate";
	}
	
	// Course data to be updated is validated
	@PostMapping("/courses/{id}")
	public String updateCourse(@PathVariable("id")Long id, 
								@RequestParam("name")String name, 
								@RequestParam("prefix")String prefix, 
								@RequestParam("description")String description, 
								@RequestParam("capacity")Long capacity, 
								RedirectAttributes flash) {
		Boolean errorsFound = false;
		if(name.length()<1) {																		//Data validation, it works, but it could be done in a cleaner fashion
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
			flash.addFlashAttribute("errors","Course not updated, please review your inputs");
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
	
	//Deletes course, as long as noone is enrolled
	@PostMapping("/courses/delete/{id}")
	public String deleteCourse(@PathVariable("id")Long id,
								RedirectAttributes flash) {
		//make sure no student is enlisted in the course to be erased, otherwise show a warning
		Course course = cS.findById(id);
		List<CourseStudent> list = course.getCoursesStudents();
		if(list.size() > 0) {
			flash.addFlashAttribute("errors", "Make sure noone is enlisted in the course to be deleted");
			return "redirect:/admin/courses";
		}
		cS.delete(cS.findById(id));
		return "redirect:/admin/courses";
	}
	
	//Page with all students registered
	@RequestMapping("/students")
	public String showStudents(Model model) {
		model.addAttribute("allStudents", uS.findAllStudents());
		return "showStudents";
	}
	
	//Page showing all courses available
	@RequestMapping("/students/{id}")																				//Needs to be polished so it shows if the student is already registered in a course
	public String seeCoursesAvailable(@PathVariable("id")Long id,													//There should also be the option to drop a course
									Model model) {
		List<Course> allCourses = cS.allCourses();
		model.addAttribute("allCourses", allCourses);
		model.addAttribute("studentId",id);
		model.addAttribute("student", uS.findById(id).getFirstName()+uS.findById(id).getLastName());
		return "showCourses";
	}
	
	//Enrolls a student to the course
	@PostMapping("/students")
	public String joinCourseStudent(@RequestParam("studentId")Long studentId,
									@RequestParam("courseId")Long courseId,
									RedirectAttributes flash) {
		User student = uS.findById(studentId);
		Course course = cS.findById(courseId);
		List<Long> students = cSS.findStudentsId(courseId);
		for(Long user: students) {
			if (studentId == user) {
				flash.addFlashAttribute("error", "Already signed to course");
				return "redirect:/admin/students/"+studentId;
			}
		}
		CourseStudent course_student = new CourseStudent();															//review, there could be an issue where once a many to many relationship between course and student, is made, cant be undone without consequences
		course_student.setCourse(course);
		course_student.setUser(student);
		cSS.save(course_student);
		return "redirect:/admin/students";
	}
	
	//Page with all teachers registered
	@RequestMapping("/teachers")
	public String showTeachers(Model model) {
		model.addAttribute("allTeachers", uS.findAllTeachers());
		return "showTeachers";
	}
}

