package com.example.webdevserverjava.models;

import javax.persistence.Entity;

@Entity
public class ListWidget extends Widget{
	private String[] items;
	private Boolean ordered;
	public String[] getItems() {
		return items;
	}
	public void setItems(String[] items) {
		this.items = items;
	}
	public Boolean getOrdered() {
		return ordered;
	}
	public void setOrdered(Boolean ordered) {
		this.ordered = ordered;
	}
}
