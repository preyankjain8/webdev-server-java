package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.webdevserverjava.model.Lesson;
import com.example.webdevserverjava.model.Module;

public class LessonService {
	Lesson lesson1 = new Lesson(123,"Lesson 1", ModuleService.moduleList.get(0));
	Lesson lesson2 = new Lesson(123,"Lesson 2", ModuleService.moduleList.get(0));
	
	public static List<Lesson> lessonList = new ArrayList<Lesson>();
	{
		lessonList.add(lesson1);
		lessonList.add(lesson2);
	};
	
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
