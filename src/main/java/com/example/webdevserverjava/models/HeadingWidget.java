package com.example.webdevserverjava.models;

import javax.persistence.Entity;

@Entity
public class HeadingWidget extends Widget{
	private Integer size;
	public Integer getSize() {
		return size;
	}
	
	public void getHeadingWidget(Widget widget) {
		this.id = widget.id;
		this.title = widget.title;
		this.text = widget.text;
		this.size = widget.size;
		this.type = "HEADING";
		this.topic = widget.topic;
	}
	
	public void set(Widget widget) {
		this.title = widget.title;
		this.text = widget.text;
		this.size = widget.size;
		this.type = widget.type;
	}
	
	
	
	public void setSize(Integer size) {
		this.size = size;
	}
}
