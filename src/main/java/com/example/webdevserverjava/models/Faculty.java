package com.example.webdevserverjava.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

public class Faculty {
	private String office;
	private Boolean tenure;
	
	@OneToMany(mappedBy="author")
	private List<Course> authoredCourses;
	
	public Faculty() {}
	public Faculty(String office, Boolean tenure) {
		this.office = office;
		this.tenure = tenure;
	}
	
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public Boolean getTenure() {
		return tenure;
	}
	public void setTenure(Boolean tenure) {
		this.tenure = tenure;
	}
}
