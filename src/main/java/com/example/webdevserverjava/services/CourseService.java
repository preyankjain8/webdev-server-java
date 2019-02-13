package com.example.webdevserverjava.services;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.model.User;

import com.example.webdevserverjava.model.Course;

@RestController
public class CourseService {
	Course webDev = new Course(123,"WebDev", 
			new User(1,"jack", "1234", "Jack", "Daniel", "FACULTY"));
	Course databases = new Course(345,"DataBases",
			new User(2,"jim", "1234", "Jim", "Beam", "FACULTY"));
	
	List<Course> courseList = new ArrayList<Course>();
	{
		courseList.add(webDev);
		courseList.add(databases);
	};
	
	
	@PostMapping("/api/courses")
	public Course createCourse(@RequestBody Course newCourse,
			HttpSession session){
		if(session.getAttribute("currentUser") != null) {
			newCourse.setAuthor((User)session.getAttribute("currentUser"));
			courseList.add(newCourse);
			return newCourse;
		}
		return null;
	}

}
