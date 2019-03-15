package com.example.webdevserverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.models.Lesson;
import com.example.webdevserverjava.models.Module;
import com.example.webdevserverjava.repositories.LessonRepository;
import com.example.webdevserverjava.repositories.ModuleRepository;
import com.example.webdevserverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class LessonService {
	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private TopicRepository topicRepository;
	@PostMapping("/api/module/{mid}/lesson")
	public Lesson createLesson(@PathVariable("mid") Integer moduleId,
			@RequestBody Lesson lesson){
		Module module = moduleRepository.findById(moduleId).get();
		lesson.setModule(module);
		return lessonRepository.save(lesson);
	}
	
	@GetMapping("/api/module/{mid}/lesson")
	public List<Lesson> findAllLessons(@PathVariable("mid") Integer moduleId){
		return moduleRepository.findById(moduleId).get().getLessons();
	}
	
	
	@GetMapping("/api/lesson/{lid}")
	public Lesson findLessonById(@PathVariable("lid") Integer lessonId){
		return lessonRepository.findById(lessonId).get();
	}
	
	@PutMapping("/api/lessons/{lid}")
	public Lesson updateLesson(@PathVariable("lid") Integer lessonId,
			@RequestBody Lesson lesson){
		Lesson l = lessonRepository.findById(lessonId).get();
		l.set(lesson);
		return lessonRepository.save(l);
	}
	
	@DeleteMapping("/api/lesson/{lid}")
	public void DeleteLesson(@PathVariable("lid") Integer lessonId){
		topicRepository.deleteAll(lessonRepository.findById(lessonId).get().getTopics());
		lessonRepository.deleteById(lessonId);
		
	}

}
