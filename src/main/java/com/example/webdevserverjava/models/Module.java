package com.example.webdevserverjava.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Module {
	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	
	@ManyToOne()
	@JsonIgnore
	private Course course;
	@OneToMany(mappedBy="module")
	private List<Lesson> lessons;
	
	public void lesson(Lesson lesson) {
		this.lessons.add(lesson);
	     if(lesson.getModule() != this) {
	        lesson.setModule(this);
	        }
	}
	
	public Module(Integer id, String title, List<Lesson> lessons) {
		this.id = id;
		this.title = title;
		this.lessons = lessons;
	}
	
	
	public void set(Module module) {
		this.title = module.title;
	}
	
	public Module(Integer id, String title, Course course) {
		this.id = id;
		this.title = title;
		this.setCourse(course);
	}
	
	
	public Module() {
		
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
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
		if(!course.getModules().contains(this)) {
			course.getModules().add(this);
		}
	}
}
