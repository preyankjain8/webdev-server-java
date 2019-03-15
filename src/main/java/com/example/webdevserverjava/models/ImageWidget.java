package com.example.webdevserverjava.models;

import javax.persistence.Entity;

@Entity
public class ImageWidget extends Widget{
	String src;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
}
