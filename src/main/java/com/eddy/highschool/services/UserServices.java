package com.eddy.highschool.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eddy.highschool.models.Role;
import com.eddy.highschool.models.User;
import com.eddy.highschool.repositories.RoleRepository;
import com.eddy.highschool.repositories.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository uR;
	private RoleRepository rR;
	private BCryptPasswordEncoder bCrypt;
	
	public UserServices(UserRepository uR, RoleRepository rR, BCryptPasswordEncoder bCrypt) {
		this.uR = uR;
		this.rR = rR;
		this.bCrypt = bCrypt;
		
		init();
	}
	
	public void init() {
		if (rR.findAll().size() <1) {
			
			Role admin = new Role();
			admin.setName("ROLE_ADMIN");
			rR.save(admin);
			
			Role teacher = new Role();
			teacher.setName("ROLE_TEACHER");
			rR.save(teacher);
			
			Role user = new Role();
			user.setName("ROLE_STUDENT");
			rR.save(user);
			
		}
		
		if (uR.findAll().size() <1) {
			User user = new User();
			user.setUsername("admin@admin");
			String rawPassword = "1234qwer";
			user.setPassword(bCrypt.encode(rawPassword));
			user.setRoles(rR.findByName("ROLE_ADMIN"));
			uR.save(user);
		}
	}
	
	public void saveWithStudentRole(User user) {
		user.setPassword(bCrypt.encode(user.getPassword()));
		user.setRoles(rR.findByName("ROLE_STUDENT"));
		uR.save(user);
	}
	
	public void saveWithTeacherRole(User user) {
		user.setPassword(bCrypt.encode(user.getPassword()));
		user.setRoles(rR.findByName("ROLE_TEACHER"));
		uR.save(user);
		}
	
	public void saveWithAdminRole(User user) {
		user.setPassword(bCrypt.encode(user.getPassword()));
		user.setRoles(rR.findByName("ROLE_ADMIN"));
		uR.save(user);
	}
	
	public List<User> findAll(){
		return (List<User>) this.uR.findAll();
	}
	
	public User findByEmail(String email) {
		return uR.findByEmail(email);
	}
	
	public User findByUsername(String username) {
		return uR.findByUsername(username);
	}
	
	public User findById(Long id) {
		return uR.findById(id).orElse(null);
	}
	
	public List<User> findAllTeachers(){
		List<User> allTeachers= new ArrayList<>();
		for(String username: uR.findAllUsersTeachers()) {
			allTeachers.add( findByUsername(username) );
		}
		return allTeachers;
	}
	
	public List<User> findAllStudents(){
		List<User> allStudents = new ArrayList<>();
		for(String username: uR.findAllUsersStudents()) {
			allStudents.add(findByUsername(username));
		}
		return allStudents;
	}
	
	public void update(User user) {
		this.uR.save(user);
	}
	
}
