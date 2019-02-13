package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.model.User;

import com.example.webdevserverjava.model.Course;
import com.example.webdevserverjava.model.Module;;

@RestController
public class CourseService {
	
	Course webDev = new Course(123,"WebDev", new ArrayList<Module>());
	Course databases = new Course(345,"DataBases", new ArrayList<Module>());
	
	List<Course> courseList = new ArrayList<Course>();
	{
		courseList.add(webDev);
		courseList.add(databases);
	};
	
	
	@PostMapping("/api/courses")
	public Course createCourse(@RequestBody Course newCourse){
		courseList.add(newCourse);
		return newCourse;
	}

}
