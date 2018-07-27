package com.eddy.highschool.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eddy.highschool.models.Course;
import com.eddy.highschool.models.CourseStudent;
import com.eddy.highschool.models.User;
import com.eddy.highschool.services.CourseServices;
import com.eddy.highschool.services.CourseStudentService;
import com.eddy.highschool.services.UserServices;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	private UserServices uS;
	private CourseServices cS;
	private CourseStudentService cSS;
	
	public TeacherController(UserServices uS, CourseServices cS, CourseStudentService cSS) {
		this.uS = uS;
		this.cS = cS;
		this.cSS = cSS;
	}
	
	//Teacher's homepage shows courses assigned to the teacher
	@RequestMapping("/homepage")
	public String teacherHomePage(Principal principal, 
									Model model) {
		String username = principal.getName();
		User user = uS.findByUsername(username);
		List<Course> courses = user.getCourses();
		model.addAttribute("currentUser",user);
		model.addAttribute("courses",courses);
		return "teacher/teacherHomePage";
	}
	
	//shows list of students assigned to a specific course
	@RequestMapping("/homepage/{id}")
	public String studentsEnrolled(@PathVariable ("id") Long id, 
									Model model) {
		Course course = cS.findById(id);
		List<User> students = cSS.findStudents(id);
		model.addAttribute("students", students);
		model.addAttribute("course", course);
		return "teacher/studentsEnrolled";
	}
	
	//Assigns grade to student
	@PostMapping("/assignGrade")
	public String assignGrade(@RequestParam("studentId")Long studentId,
							@RequestParam("courseId")Long courseId,
							@RequestParam("finalGrade")Long finalGrade) {
		ArrayList<CourseStudent> courseStudents = (ArrayList<CourseStudent>) this.cSS.findAll();
		for(CourseStudent var: courseStudents) {
			if(var.getCourse().getId() == courseId && var.getUser().getId() == studentId) {
				var.setFinalGrade(finalGrade);
				cSS.save(var);
				break;
			}
		}
		return "redirect:/teacher/homepage/" + courseId;
	}
}
