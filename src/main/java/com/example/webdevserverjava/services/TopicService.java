package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.webdevserverjava.model.Lesson;
import com.example.webdevserverjava.model.Topic;

public class TopicService {
	Topic topic1 = new Topic(123,"Topic 1", LessonService.lessonList.get(0));
	Topic topic2 = new Topic(123,"Topic 2", LessonService.lessonList.get(0));
	
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
	
}
