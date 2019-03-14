package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
public class ModuleService {
	Module react;
	Module redux;
	{
		CourseService.getInstance();
		react = new Module(123,"React", CourseService.courseList.get(0));
		redux = new Module(234,"Redux", CourseService.courseList.get(0));
		if(moduleList.size() ==  0)
			moduleList.add(react);
		if(moduleList.size() ==  1)
			moduleList.add(redux);
		
	};
	private static ModuleService moduleService;
	
	public static List<Module> moduleList = new ArrayList<Module>();
	
	
	public static ModuleService getInstance() {
		if (moduleService == null)
			moduleService = new ModuleService();
		return moduleService;
	}
	
	@PostMapping("/api/courses/{cid}/modules")
	public Module createModule(@PathVariable("cid") Integer courseId,
			@RequestBody Module module){
		for (Course course : CourseService.courseList) {
			if (course.getId().equals(courseId)) {
				module.setCourse(course);
				moduleList.add(module);
				return module;
			}
		}
		return null;
	}
	
	@GetMapping("/api/course/{cid}/modules")
	public List<Module> findAllModules(@PathVariable("cid") Integer courseId,
			HttpSession session){
		if(session.getAttribute("currentUser") == null) {
			return null;
		}
		List<Module> moduleListTemp = new ArrayList<Module>();
		for (Module module : moduleList) {
			if (module.getCourse().getId().equals(courseId)) {
				moduleListTemp.add(module);
			}
		}
		return moduleListTemp;
	}
	
	
	@GetMapping("/api/modules/{mid}")
	public Module findModuleById(@PathVariable("mid") Integer moduleId){
		for (Module module : moduleList) {
			if (module.getId().equals(moduleId)) {
				return module;
			}
		}
		return null;
	}
	
	@PutMapping("/api/modules/{mid}")
	public Module updateModule(@PathVariable("mid") Integer moduleId,
			@RequestBody Module module){
		Module moduleToEdit = null;
		int index = 0;
		for (Module m : moduleList) {
			if (m.getId().equals(moduleId)) {
				moduleToEdit = m;
				break;
			}
			index += 1;
		}
		moduleToEdit.setTitle(module.getTitle());
		moduleList.set(index, moduleToEdit);
		return moduleToEdit;
	}
	
	
	@DeleteMapping("/api/modules/{mid}")
	public void deleteModule(@PathVariable("mid") Integer moduleId){
		Module moduleForDeletion = null;
		for (Module module : moduleList) {
			if (module.getId().equals(moduleId)) {
				moduleForDeletion = module;
				break;
			}
		}
		moduleList.remove(moduleForDeletion);
	}
	
}
