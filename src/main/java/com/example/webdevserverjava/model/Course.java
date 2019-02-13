package com.example.webdevserverjava.model;

import java.util.List;

public class Course {
	private Integer id;
	private String title;
	private List<Module> modules;
	
	public Course(Integer id, String title, List<Module> modules) {
		this.id = id;
		this.title = title;
		this.modules = modules;
	}
	
	public Course(Integer id, String title) {
		this.id = id;
		this.title = title;
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
}
