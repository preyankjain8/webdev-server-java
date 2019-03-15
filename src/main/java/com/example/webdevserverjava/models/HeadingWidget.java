package com.example.webdevserverjava.models;

import javax.persistence.Entity;

@Entity
public class HeadingWidget extends Widget{
	private Integer size;
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
