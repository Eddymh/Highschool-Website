package com.eddy.highschool.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=1, max=255, message="Email required")
	@Email
	private String email;
	
	@Size(min=5, max=255, message="Username must be greater than 5 characters")
	private String username;
	
	@Size(min=1, max=255)
	private String firstName;
	
	@Size(min=1, max=255)
	private String lastName;
	
	@Size(min=1, max=255)
	private String street;
	
	@Size(min=1, max=255)
	private String city;
	
	@Size(min=1, max=255)
	private String zipCode;
	
	@Size(min=10, max=11)
	private String phoneNumber;
	
	@Size(min=8, max=255)
	private String password;
	
	@Transient
	@Size(min=8, max=255)
	private String confirm;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date createdAt;

	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date updatedAt;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="users_roles", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	
	//Teachers can teach many courses
	@OneToMany(mappedBy="teacher", fetch = FetchType.LAZY)
	private List<Course> courses;
	
	//A student can take many courses
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="students_coursesTaken",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> coursesTaken;*/
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<CourseStudent> coursesStudents;
	
	public User() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
		//this.username = this.email;
	}
	
	@PrePersist
	protected void onCreate(){
	    this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
	    this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<CourseStudent> getCoursesStudents() {
		return coursesStudents;
	}

	public void setCoursesStudents(List<CourseStudent> coursesStudents) {
		this.coursesStudents = coursesStudents;
	}
	
}
