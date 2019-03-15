package com.example.webdevserverjava.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.models.Widget;
import com.example.webdevserverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class WidgetService {
	@Autowired
	private WidgetRepository widgetRepository;
	
	@GetMapping("/api/widget/{wid}")
	public Widget findWidgetById(@PathVariable("wid") Integer widgetId){
		return widgetRepository.findById(widgetId).get();
	}
	
	@PutMapping("/api/widget/{wid}")
	public Widget updateWidget(@PathVariable("wid") Integer widgetId,
			@RequestBody Widget widget){
		Widget w = widgetRepository.findById(widgetId).get();
		w.set(widget);
		return widgetRepository.save(w);
	}
	
	@DeleteMapping("/api/widget/{wid}")
	public void deleteWidget(@PathVariable("wid") Integer widgetId){
		widgetRepository.deleteById(widgetId);
	}
}
