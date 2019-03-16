package com.example.webdevserverjava.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lesson {
	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	@ManyToOne()
	@JsonIgnore
	private Module module;
	@OneToMany(mappedBy="lesson", cascade = CascadeType.REMOVE,
			orphanRemoval = true)
	private List<Topic> topics;
	
	
	public void topic(Topic topic) {
		this.topics.add(topic);
	     if(topic.getLesson() != this) {
	        topic.setLesson(this);
	        }
	}
	
	public void set(Lesson lesson) {
		this.title = lesson.title;
	}
	
	public Lesson(Integer id, String title, List<Topic> topics) {
		this.id = id;
		this.title = title;
		this.topics = topics;
	}
	
	public Lesson(Integer id, String title, Module module) {
		this.id = id;
		this.title = title;
		this.module = module;
	}
	
	public Lesson() {
		
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
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics= topics;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
		if(!module.getLessons().contains(this)) {
			module.getLessons().add(this);
		}
	}
}
