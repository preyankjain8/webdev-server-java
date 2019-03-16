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
import com.example.webdevserverjava.models.ParagraphWidget;
import com.example.webdevserverjava.models.Widget;
import com.example.webdevserverjava.repositories.HeadingWidgetRepository;
import com.example.webdevserverjava.repositories.ListWidgetRepository;
import com.example.webdevserverjava.repositories.ParagraphWidgetRepository;
import com.example.webdevserverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class HeadingWidgetService {
	@Autowired
	private HeadingWidgetRepository headingWidgetRepository;
	@Autowired
	private WidgetRepository widgetRepository;
	
	@GetMapping("/api/heading/widget/{wid}")
	public Widget findWidgetById(@PathVariable("wid") Integer widgetId){
		return headingWidgetRepository.findById(widgetId).get();
	}
	
	@PutMapping("/api/heading/widget/{wid}")
	public Widget updateWidget(@PathVariable("wid") Integer widgetId,
			@RequestBody Widget widget){
		HeadingWidget w = null;
		try {
			w = headingWidgetRepository.findById(widgetId).get();
		} catch (NoSuchElementException ex) {
			if (w == null) {
				w = new HeadingWidget();
				Widget widg = widgetRepository.findById(widgetId).get();
				w.getHeadingWidget(widg);
				widgetRepository.deleteById(widgetId);
			}
		}
		w.set(widget);
		return headingWidgetRepository.save(w);
	}
	
	@DeleteMapping("/api/heading/widget/{wid}")
	public void deleteWidget(@PathVariable("wid") Integer widgetId){
		headingWidgetRepository.deleteById(widgetId);
	}
}
