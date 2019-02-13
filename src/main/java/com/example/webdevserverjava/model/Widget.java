package com.example.webdevserverjava.model;

import java.util.List;

public class Widget {
	private Integer id;
	private String title;
	private String type;
	private String text;
	private Integer size;
	
	public Widget(Integer id, String title, String type, String text, Integer size) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.text = text;
		this.size = size;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}
