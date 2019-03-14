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

import com.example.webdevserverjava.models.Course;
import com.example.webdevserverjava.models.Lesson;
import com.example.webdevserverjava.models.Module;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class LessonService {
	Lesson lesson1;
	Lesson lesson2; 
	{
		ModuleService.getInstance();
		lesson1 = new Lesson(123,"Lesson 1", ModuleService.moduleList.get(0));
		lesson2 = new Lesson(234,"Lesson 2", ModuleService.moduleList.get(0));
		if(lessonList.size() == 0)
			lessonList.add(lesson1);
		if(lessonList.size() == 1)
			lessonList.add(lesson2);
	}
	private static LessonService lessonService;
	
	public static LessonService getInstance() {
		if(lessonService == null)
			lessonService = new LessonService();
		return lessonService;
	}
	
	public static List<Lesson> lessonList = new ArrayList<Lesson>();
	
	@PostMapping("/api/module/{mid}/lesson")
	public Lesson createLesson(@PathVariable("mid") Integer moduleId,
			@RequestBody Lesson lesson){
		for (Module module : ModuleService.moduleList) {
			if (module.getId().equals(moduleId)) {
				lesson.setModule(module);
				lessonList.add(lesson);
				return lesson;
			}
		}
		return null;
	}
	
	@GetMapping("/api/module/{mid}/lesson")
	public List<Lesson> findAllLessons(@PathVariable("mid") Integer moduleId){
		List<Lesson> lessonListTemp = new ArrayList<Lesson>();
		for (Lesson lesson : lessonList) {
			if (lesson.getModule().getId().equals(moduleId)) {
				lessonListTemp.add(lesson);
			}
		}
		return lessonListTemp;
	}
	
	
	@GetMapping("/api/lesson/{lid}")
	public Lesson findLessonById(@PathVariable("lid") Integer lessonId){
		for (Lesson lesson : lessonList) {
			if (lesson.getId().equals(lessonId)) {
				return lesson;
			}
		}
		return null;
	}
	
	@PutMapping("/api/lessons/{lid}")
	public Lesson updateLesson(@PathVariable("lid") Integer lessonId,
			@RequestBody Lesson lesson){
		Lesson lessonToEdit = null;
		int index = 0;
		for(Lesson l : lessonList) {
			if(l.getId().equals(lessonId)) {
				lessonToEdit = l;
				break;
			}
			index += 1;
		}
		lessonToEdit.setTitle(lesson.getTitle());
		lessonList.set(index, lessonToEdit);
		return lessonToEdit;
	}
	
	
	@DeleteMapping("/api/lesson/{lid}")
	public void DeleteLesson(@PathVariable("lid") Integer lessonId){
		Lesson lessonForDeletion = null;
		for (Lesson lesson : lessonList) {
			if (lesson.getId().equals(lessonId)) {
				lessonForDeletion = lesson;
				break;
			}
		}
		lessonList.remove(lessonForDeletion);
	}

}
