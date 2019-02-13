package com.example.webdevserverjava.model;

import java.util.List;

public class Module {
	private Integer id;
	private String title;
	private Course course;
	private List<Lesson> lessons;
	
	public Module(Integer id, String title, List<Lesson> lessons) {
		this.id = id;
		this.title = title;
		this.lessons = lessons;
	}
	
	public Module(Integer id, String title, Course course) {
		this.id = id;
		this.title = title;
		this.setCourse(course);
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
	}
}
