package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.model.Course;
import com.example.webdevserverjava.model.Lesson;
import com.example.webdevserverjava.model.Module;
import com.example.webdevserverjava.model.Topic;

@RestController
//@CrossOrigin(origins = "https://polar-cliffs-65655.herokuapp.com",
@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class TopicService {
	Topic topic1 = new Topic(123,"Topic 1", LessonService.lessonList.get(0));
	Topic topic2 = new Topic(234,"Topic 2", LessonService.lessonList.get(0));
	
	public static List<Topic> topicList = new ArrayList<Topic>();
	{
		topicList.add(topic1);
		topicList.add(topic2);
	};
	
	@PostMapping("/api/lesson/{lid}/topic")
	public Topic createTopic(@PathVariable("lid") Integer lessonId,
			@RequestBody Topic topic){
		for (Lesson lesson : LessonService.lessonList) {
			if (lesson.getId().equals(lessonId)) {
				topic.setLesson(lesson);
				topicList.add(topic);
				return topic;
			}
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lid}/topic")
	public List<Topic> findAllTopics(@PathVariable("lid") Integer lessonId){
		List<Topic> topicListTemp = new ArrayList<Topic>();
		for (Topic topic : topicList) {
			if (topic.getLesson().getId().equals(lessonId)) {
				topicListTemp.add(topic);
			}
		}
		return topicListTemp;
	}
	
	@GetMapping("/api/topic/{tid}")
	public Topic findTopicById(@PathVariable("tid") Integer topicId){
		for (Topic topic : topicList) {
			if (topic.getId().equals(topicId)) {
				return topic;
			}
		}
		return null;
	}
	
	@PutMapping("/api/topic/{tid}")
	public Topic updateTopic(@PathVariable("tid") Integer topicId,
			@RequestBody Topic topic){
		Topic topicToEdit = null;
		int index = 0;
		for(Topic t : topicList) {
			if(t.getId().equals(topicId)) {
				topicToEdit = t;
				break;
			}
			index += 1;
		}
		topicToEdit.setTitle(topic.getTitle());
		topicList.set(index, topicToEdit);
		return topicToEdit;
	}
	
	@DeleteMapping("/api/topic/{tid}")
	public void deleteCourse(@PathVariable("tid") Integer topicId){
		Topic deletTopicVar = null;
		for(Topic t : topicList) {
			if(t.getId().equals(topicId)) {
				deletTopicVar = t;
				break;
			}
		}
		topicList.remove(deletTopicVar);
	}
	
}
