package com.example.webdevserverjava.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.models.Course;
import com.example.webdevserverjava.models.Lesson;
import com.example.webdevserverjava.models.Module;
import com.example.webdevserverjava.models.Topic;
import com.example.webdevserverjava.models.Widget;
import com.example.webdevserverjava.repositories.CourseRepository;
import com.example.webdevserverjava.repositories.LessonRepository;
import com.example.webdevserverjava.repositories.ModuleRepository;
import com.example.webdevserverjava.repositories.TopicRepository;
import com.example.webdevserverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*",
allowCredentials= "true",
allowedHeaders = "*")
public class StudentServices {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private WidgetRepository widgetRepository;
	@GetMapping("/api/student/courses")
	public List<Course> findAllCourses(HttpSession session){
		return (List<Course>)courseRepository.findAll();
	}
	@GetMapping("/api/student/course/{cid}/modules")
	public List<Module> findAllModules(@PathVariable("cid") Integer courseId,
			HttpSession session){
		return courseRepository.findById(courseId).get().getModules();
	}
	@GetMapping("/api/student/module/{mid}/lesson")
	public List<Lesson> findAllLessons(@PathVariable("mid") Integer moduleId){
		return moduleRepository.findById(moduleId).get().getLessons();
	}
	@GetMapping("/api/student/lesson/{lid}/topic")
	public List<Topic> findAllTopics(@PathVariable("lid") Integer lessonId){
		return lessonRepository.findById(lessonId).get().getTopics();
	}
	@GetMapping("/api/student/topic/{tid}/widget")
	public List<Widget> findAllWidgets(@PathVariable("tid") Integer topicId){
		return topicRepository.findById(topicId).get().getWidgets();
	}
	@GetMapping("/api/student/courses/{cid}")
	public Course findCourseById(@PathVariable("cid") Integer courseId){
		return courseRepository.findById(courseId).get();
	}
	@GetMapping("/api/student/courses/{cid}/author")
	public String findCourseAuthor(@PathVariable("cid") Integer courseId){
		return courseRepository.findById(courseId).get().getAuthor().getFirstName();
	}
	@GetMapping("/api/student/modules/{mid}")
	public Module findModuleById(@PathVariable("mid") Integer moduleId){
		return moduleRepository.findById(moduleId).get();
	}
	@GetMapping("/api/student/lesson/{lid}")
	public Lesson findLessonById(@PathVariable("lid") Integer lessonId){
		return lessonRepository.findById(lessonId).get();
	}
	@GetMapping("/api/student/topic/{tid}")
	public Topic findTopicById(@PathVariable("tid") Integer topicId){
		return topicRepository.findById(topicId).get();
	}
	@GetMapping("/api/student/widget/{wid}")
	public Widget findWidgetById(@PathVariable("wid") Integer widgetId){
		return widgetRepository.findById(widgetId).get();
	}
}
