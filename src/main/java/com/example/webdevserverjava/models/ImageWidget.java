package com.example.webdevserverjava.models;

import javax.persistence.Entity;

@Entity
public class ImageWidget extends Widget{
	String src;
	
	public void set(Widget widget) {
		this.title = widget.title;
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
