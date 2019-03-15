package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.example.webdevserverjava.repositories.CourseRepository;
import com.example.webdevserverjava.repositories.LessonRepository;
import com.example.webdevserverjava.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class ModuleService {
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private LessonRepository lessonRepository;
	@PostMapping("/api/courses/{cid}/modules")
	public Module createModule(@PathVariable("cid") Integer courseId,
			@RequestBody Module module){
		Course course = courseRepository.findById(courseId).get();
		module.setCourse(course);
		return moduleRepository.save(module);
	}
	
	@GetMapping("/api/course/{cid}/modules")
	public List<Module> findAllModules(@PathVariable("cid") Integer courseId,
			HttpSession session){
		if(session.getAttribute("currentUser") == null) {
			return null;
		}
		return courseRepository.findById(courseId).get().getModules();
	}
	
	
	@GetMapping("/api/modules/{mid}")
	public Module findModuleById(@PathVariable("mid") Integer moduleId){
		return moduleRepository.findById(moduleId).get();
	}
	
	@PutMapping("/api/modules/{mid}")
	public Module updateModule(@PathVariable("mid") Integer moduleId,
			@RequestBody Module module){
		Module m = moduleRepository.findById(moduleId).get();
		m.set(module);
		return moduleRepository.save(m);
	}
	
	
	@DeleteMapping("/api/modules/{mid}")
	public void deleteModule(@PathVariable("mid") Integer moduleId){
		lessonRepository.deleteAll(moduleRepository.findById(moduleId).get().getLessons());
		moduleRepository.deleteById(moduleId);
	}
	
}
