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

import com.example.webdevserverjava.models.HeadingWidget;
import com.example.webdevserverjava.models.ImageWidget;
import com.example.webdevserverjava.models.ListWidget;
import com.example.webdevserverjava.models.Widget;
import com.example.webdevserverjava.repositories.ListWidgetRepository;
import com.example.webdevserverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class ListWidgetService {
	@Autowired
	private ListWidgetRepository listWidgetRepository;
	@Autowired
	private WidgetRepository widgetRepository;
	
	@GetMapping("/api/list/widget/{wid}")
	public Widget findWidgetById(@PathVariable("wid") Integer widgetId){
		return listWidgetRepository.findById(widgetId).get();
	}
	
	@PutMapping("/api/list/widget/{wid}")
	public Widget updateWidget(@PathVariable("wid") Integer widgetId,
			@RequestBody Widget widget){
		
		ListWidget w = null;
		try {
			w = listWidgetRepository.findById(widgetId).get();
		} catch (NoSuchElementException ex) {
			if (w == null) {
				w = new ListWidget();
				Widget widg = widgetRepository.findById(widgetId).get();
				w.getListWidget(widg);
				widgetRepository.deleteById(widgetId);
			}
		}
		w.set(widget);
		return listWidgetRepository.save(w);
	}
	
	@DeleteMapping("/api/list/widget/{wid}")
	public void deleteWidget(@PathVariable("wid") Integer widgetId){
		listWidgetRepository.deleteById(widgetId);
	}
}
