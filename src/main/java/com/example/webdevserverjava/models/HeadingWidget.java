package com.example.webdevserverjava.models;

import javax.persistence.Entity;

@Entity
public class HeadingWidget extends Widget{
	private Integer size;
	public Integer getSize() {
		return size;
	}
	
	public void set(Widget widget) {
		this.title = widget.title;
		this.text = widget.text;
		this.size = widget.size;
	}
	
	public void setSize(Integer size) {
		this.size = size;
	}
}
