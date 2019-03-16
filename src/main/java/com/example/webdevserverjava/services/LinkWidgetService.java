package com.example.webdevserverjava.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.models.LinkWidget;
import com.example.webdevserverjava.models.ListWidget;
import com.example.webdevserverjava.models.Widget;
import com.example.webdevserverjava.repositories.LinkWidgetRepository;
import com.example.webdevserverjava.repositories.ListWidgetRepository;
import com.example.webdevserverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class LinkWidgetService {
	@Autowired
	private LinkWidgetRepository linkWidgetRepository;
	@Autowired
	private WidgetRepository widgetRepository;
	
	@GetMapping("/api/link/widget/{wid}")
	public Widget findWidgetById(@PathVariable("wid") Integer widgetId){
		return linkWidgetRepository.findById(widgetId).get();
	}
	
	@PutMapping("/api/link/widget/{wid}")
	public Widget updateWidget(@PathVariable("wid") Integer widgetId,
			@RequestBody Widget widget){
		
		LinkWidget w = null;
		try {
			w = linkWidgetRepository.findById(widgetId).get();
		} catch (NoSuchElementException ex) {
			if (w == null) {
				w = new LinkWidget();
				Widget widg = widgetRepository.findById(widgetId).get();
				w.getLinkWidget(widg);
				widgetRepository.deleteById(widgetId);
			}
		}
		w.set(widget);
		return linkWidgetRepository.save(w);
	}
	
	@DeleteMapping("/api/link/widget/{wid}")
	public void deleteWidget(@PathVariable("wid") Integer widgetId){
		linkWidgetRepository.deleteById(widgetId);
	}
}
