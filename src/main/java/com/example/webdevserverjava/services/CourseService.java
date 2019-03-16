package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.models.Course;
import com.example.webdevserverjava.models.User;
import com.example.webdevserverjava.repositories.CourseRepository;
import com.example.webdevserverjava.repositories.LessonRepository;
import com.example.webdevserverjava.repositories.ModuleRepository;
import com.example.webdevserverjava.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "https://polar-cliffs-65655.herokuapp.com",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@PostMapping("/api/courses")
	@ResponseBody
	public Course createCourse(@RequestBody Course newCourse,
			HttpSession session){
		if(session.getAttribute("currentUser") != null) {
			newCourse.setAuthor((User)session.getAttribute("currentUser"));
			return courseRepository.save(newCourse);
		}
		return null;
	}
	
	@GetMapping("/api/courses")
	public List<Course> findAllCourses(HttpSession session){
		if(session.getAttribute("currentUser") == null) {
			return null;
		}
		User currentAuthor = (User)session.getAttribute("currentUser");
		return userRepository.findById(currentAuthor.getId()).get().getAuthoredCourses();
	}
	
	
	@GetMapping("/api/courses/{cid}")
	public Course findCourseById(@PathVariable("cid") Integer courseId){
		return courseRepository.findById(courseId).get();
	}
	
	@PutMapping("/api/courses/{cid}")
	public Course updateCourse(@PathVariable("cid") Integer courseId,
			@RequestBody Course course){
		Course c = courseRepository.findById(courseId).get();
		c.set(course);
		return courseRepository.save(c);
	}
	
	@DeleteMapping("/api/courses/{cid}")
	public void deleteCourse(@PathVariable("cid") Integer courseId){
		//moduleRepository.deleteAll(courseRepository.findById(courseId).get().getModules());
		courseRepository.deleteById(courseId);
	}
	
	
}