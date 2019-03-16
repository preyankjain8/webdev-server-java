package com.example.webdevserverjava.models;

import javax.persistence.Entity;

@Entity
public class LinkWidget extends Widget{
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void getLinkWidget(Widget widget) {
		this.title = widget.title;
		this.text = widget.text;
		this.url = widget.text;
		this.type = "HEADING";
		this.topic = widget.topic;
	}
	
	public void set(Widget widget) {
		this.title = widget.title;
		this.text = widget.text;
		this.url = widget.text;
		this.type = widget.type;
	}
}
