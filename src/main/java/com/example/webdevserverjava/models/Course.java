package com.example.webdevserverjava.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
public class Course {
	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	
	@ManyToOne()
	@JsonIgnore
	private User author;
	
	@OneToMany(mappedBy="course")
	private List<Module> modules;
	
	public void module(Module module) {
		this.modules.add(module);
	     if(module.getCourse() != this) {
	        module.setCourse(this);
	        }
	}
	
	public Course () {
		
	}
	public Course(Integer id, String title, List<Module> modules) {
		this.id = id;
		this.title = title;
		this.modules = modules;
	}
	
	public Course(Integer id, String title, User author) {
		this.id = id;
		this.title = title;
		this.setAuthor(author);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public User getAuthor() {
		return author;
	}

	public void set(Course newCourse) {
		this.title = newCourse.title;
	}
	public void setAuthor(User author) {
		this.author = author;
		if(!author.getAuthoredCourses().contains(this)) {
			author.getAuthoredCourses().add(this);
		}
	}
}
