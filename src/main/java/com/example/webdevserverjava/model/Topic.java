package com.example.webdevserverjava.model;

import java.util.List;

public class Topic {
	private Integer id;
	private String title;
	private Lesson lesson;
	private List<Widget> widgets;
	
	public Topic(Integer id, String title, List<Widget> widgets) {
		this.id = id;
		this.title = title;
		this.widgets = widgets;
	}
	
	public Topic(Integer id, String title, Lesson lesson) {
		this.id = id;
		this.title = title;
		this.setLesson(lesson);
	}
	
	public Topic() {
		
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
	public List<Widget> getWidgets() {
		return widgets;
	}
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
}
