package com.example.webdevserverjava.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Topic {
	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	@ManyToOne()
	@JsonIgnore
	private Lesson lesson;
	@OneToMany(mappedBy="topic")
	private List<Widget> widgets;
	
	
	public void widget(Widget widget) {
		this.widgets.add(widget);
	     if(widget.getTopic() != this) {
	        widget.setTopic(this);
	        }
	}
	
	public Topic(Integer id, String title, List<Widget> widgets) {
		this.id = id;
		this.title = title;
		this.widgets = widgets;
	}
	
	public Topic(Integer id, String title, Lesson lesson) {
		this.id = id;
		this.title = title;
		this.setLesson(lesson);
	}
	
	public Topic() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Widget> getWidgets() {
		return widgets;
	}
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}

	public Lesson getLesson() {
		return lesson;
	}
	
	public void set(Topic topic) {
		this.title = topic.title;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
		if(!lesson.getTopics().contains(this)) {
			lesson.getTopics().add(this);
		}
	}
}
