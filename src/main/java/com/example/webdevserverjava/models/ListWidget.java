package com.example.webdevserverjava.models;

import javax.persistence.Entity;

@Entity
public class ListWidget extends Widget{
	private String[] items;
	private Boolean ordered;
	
	public void set(Widget widget) {
		this.title = widget.title;
		if (widget.size == 6) {
			this.ordered = false;
		}
		else if (widget.size == 7) {
			this.ordered = true;
		}
		this.text = widget.text;
		this.type = widget.type;
	}
	
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
