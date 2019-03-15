package com.example.webdevserverjava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.models.ImageWidget;
import com.example.webdevserverjava.models.Widget;
import com.example.webdevserverjava.repositories.ImageWidgetRepository;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class ImageWidgetService {
	@Autowired
	private ImageWidgetRepository imageWidgetRepository;
	
	@GetMapping("/api/image/widget/{wid}")
	public Widget findWidgetById(@PathVariable("wid") Integer widgetId){
		return imageWidgetRepository.findById(widgetId).get();
	}
	
	@PutMapping("/api/image/widget/{wid}")
	public Widget updateWidget(@PathVariable("wid") Integer widgetId,
			@RequestBody Widget widget){
		ImageWidget w = imageWidgetRepository.findById(widgetId).get();
		w.set(widget);
		return imageWidgetRepository.save(w);
	}
	
	@DeleteMapping("/api/image/widget/{wid}")
	public void deleteWidget(@PathVariable("wid") Integer widgetId){
		imageWidgetRepository.deleteById(widgetId);
	}
}
