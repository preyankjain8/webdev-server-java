package com.example.webdevserverjava.models;

import javax.persistence.Entity;

@Entity
public class ParagraphWidget extends Widget{
	private String text;

	public String getText() {
		return text;
	}
	
	public void getParagraphWidget(Widget 	widget) {
		this.title = widget.title;
		this.text = widget.text;
		this.type = "PARAGRAPH";
		this.topic = widget.topic;
	}
	
	public void set(Widget widget) {
		this.title = widget.title;
		this.text = widget.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}
