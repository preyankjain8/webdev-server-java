package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.models.Course;
import com.example.webdevserverjava.models.Lesson;
import com.example.webdevserverjava.models.Module;
import com.example.webdevserverjava.models.Topic;
import com.example.webdevserverjava.models.Widget;
import com.example.webdevserverjava.repositories.LessonRepository;
import com.example.webdevserverjava.repositories.ModuleRepository;
import com.example.webdevserverjava.repositories.TopicRepository;
import com.example.webdevserverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class TopicService {
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private WidgetRepository widgetRepository;
	@PostMapping("/api/lesson/{lid}/topic")
	public Topic createTopic(@PathVariable("lid") Integer lessonId,
			@RequestBody Topic topic){
		Lesson lesson = lessonRepository.findById(lessonId).get();
		topic.setLesson(lesson);
		return topicRepository.save(topic);
	}
	
	@GetMapping("/api/lesson/{lid}/topic")
	public List<Topic> findAllTopics(@PathVariable("lid") Integer lessonId){
		return lessonRepository.findById(lessonId).get().getTopics();
	}
	
	@GetMapping("/api/topic/{tid}")
	public Topic findTopicById(@PathVariable("tid") Integer topicId){
		return topicRepository.findById(topicId).get();
	}
	
	@PutMapping("/api/topic/{tid}")
	public Topic updateTopic(@PathVariable("tid") Integer topicId,
			@RequestBody Topic topic){
		Topic t = topicRepository.findById(topicId).get();
		t.set(topic);
		return topicRepository.save(t);
	}
	
	@DeleteMapping("/api/topic/{tid}")
	public void deleteTopic(@PathVariable("tid") Integer topicId){
		topicRepository.deleteById(topicId);
	}
	
	@PostMapping("/api/topic/{tid}/widget")
	public Widget createWidget(@PathVariable("tid") Integer topicId,
			@RequestBody Widget widget){
		Topic topic = topicRepository.findById(topicId).get();
		widget.setType("HEADING");
		widget.setTopic(topic);
		return widgetRepository.save(widget);
	}
	
	@GetMapping("/api/topic/{tid}/widget")
	public List<Widget> findAllWidgets(@PathVariable("tid") Integer topicId){
		return topicRepository.findById(topicId).get().getWidgets();
	}
	
}
