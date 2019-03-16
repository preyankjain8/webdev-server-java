package com.example.webdevserverjava.models;
import java.util.List;

import javax.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String role;
	
	@OneToMany(mappedBy="author", cascade = CascadeType.REMOVE,
			orphanRemoval = true)
	private List<Course> authoredCourses;
	
	public void authoredCourse(Course course) {
		this.authoredCourses.add(course);
	     if(course.getAuthor() != this) {
	        course.setAuthor(this);
	        }
	}
	
	public List<Course> getAuthoredCourses() {
		return this.authoredCourses;
	}
	
	public User() {}
	public User(int id, String username, String password, String firstName, String lastName, String role) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	
	public void set(User newUser) {
		this.username = newUser.username;
		this.password = newUser.password;
		this.firstName = newUser.firstName;
		this.lastName = newUser.lastName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
