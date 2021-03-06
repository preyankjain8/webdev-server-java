package com.example.webdevserverjava.models;

import javax.persistence.Entity;

@Entity
public class ImageWidget extends Widget{
	String src;
	
	
	public void getImageWidget(Widget widget) {
		this.title = widget.title;
		this.src = widget.text;
		this.text = widget.text;
		this.type = "IMAGE";
		this.topic = widget.topic;
	}
	
	public void set(Widget widget) {
		this.title = widget.title;
		this.text = widget.text;
		this.src = widget.text;
		this.type = widget.type;
	}
	public void setImageWidget(Widget widget) {
		this.src = widget.getText();
	}
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
}
