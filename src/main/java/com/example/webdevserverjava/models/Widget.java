package com.example.webdevserverjava.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget {
	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	public Integer id;
	public String title;
	public String type;
	public String text;
	public Integer size;
	@ManyToOne()
	@JsonIgnore
	public Topic topic;
	
	public Widget() {
		
	}
	public Widget(Integer id, String title, String type, String text, Integer size, Topic topic) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.text = text;
		this.setTopic(topic);
	}
	
	public void set(Widget widget) {
		this.title = widget.title;
		this.text = widget.text;
		this.type = widget.type; 
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return title;
	}
	public void setName(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
		if(!topic.getWidgets().contains(this)) {
			topic.getWidgets().add(this);
		}
	}
}
