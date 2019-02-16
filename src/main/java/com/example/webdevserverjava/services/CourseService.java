package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.model.User;

import com.example.webdevserverjava.model.Course;

@RestController
@CrossOrigin(origins = "https://polar-cliffs-65655.herokuapp.com",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class CourseService {
	private static CourseService courseService;
	Course webDev;
	Course databases;
	{
		UserService.getInstance();
		webDev = new Course(123,"WebDev", UserService.usersList.get(0));
		databases = new Course(345,"DataBases",UserService.usersList.get(0));
		if(courseList.size() == 0)
			courseList.add(webDev);
		if(courseList.size() == 1)
			courseList.add(databases);
	}
	
	public static CourseService getInstance() {
		if(courseService == null)
			courseService = new CourseService();
		return courseService;
	}
	
	public static List<Course> courseList = new ArrayList<Course>();
	public CourseService() {
	}	
	
	@PostMapping("/api/courses")
	@ResponseBody
	public Course createCourse(@RequestBody Course newCourse,
			HttpSession session){
		if(session.getAttribute("currentUser") != null) {
			newCourse.setAuthor((User)session.getAttribute("currentUser"));
			courseList.add(newCourse);
			return newCourse;
		}
		return null;
	}
	
	@GetMapping("/api/courses")
	public List<Course> findAllCourses(HttpSession session){
		List<Course> tempCourseList = new ArrayList<Course>();
		if(session.getAttribute("currentUser") == null) {
			return null;
		}
		User currentAuthor = (User)session.getAttribute("currentUser");
		for(Course course : courseList) {
			if(course.getAuthor().equals(currentAuthor)) {
				tempCourseList.add(course);
			}
		}
		return tempCourseList;
	}
	
	
	@GetMapping("/api/courses/{cid}")
	public Course findCourseById(@PathVariable("cid") Integer courseId){
		for(Course course : courseList) {
			if(course.getId().equals(courseId)) {
				return course;
			}
		}
		return null;
	}
	
	@PutMapping("/api/courses/{cid}")
	public Course updateCourse(@PathVariable("cid") Integer courseId,
			@RequestBody Course course){
		int index = 0;
		Course courseToEdit = null;
		for(Course c : courseList) {
			if(c.getId().equals(courseId)) {
				courseToEdit = c;
				break;
			}
			index += 1;
		}
		courseToEdit.setTitle(course.getTitle());
		courseList.set(index, courseToEdit);
		
		return courseToEdit;
	}
	
	@DeleteMapping("/api/courses/{cid}")
	public void deleteCourse(@PathVariable("cid") Integer courseId){
		Course deletCourseVar = null;
		for(Course c : courseList) {
			if(c.getId().equals(courseId)) {
				deletCourseVar = c;
				break;
			}
		}
		
		courseList.remove(deletCourseVar);
	}
	
	
}