package com.example.webdevserverjava.models;

import javax.persistence.Entity;

@Entity
public class ParagraphWidget extends Widget{
	private String text;

	public String getText() {
		return text;
	}

	public void set(Widget widget) {
		this.title = widget.title;
		this.text = widget.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}
