package com.example.webdevserverjava.services;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.model.User;

import com.example.webdevserverjava.model.Course;;

@RestController
public class CourseService {
	
	@PostMapping("/api/courses")
	public Course createCourse(@RequestBody User newCourse,
			HttpSession session) {
		return null;
	}

}
