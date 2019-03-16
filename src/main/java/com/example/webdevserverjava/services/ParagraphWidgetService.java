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

import com.example.webdevserverjava.models.ParagraphWidget;
import com.example.webdevserverjava.models.Widget;
import com.example.webdevserverjava.repositories.ParagraphWidgetRepository;
import com.example.webdevserverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class ParagraphWidgetService {
	@Autowired
	private ParagraphWidgetRepository paragraphWidgetRepository;
	@Autowired
	private WidgetRepository widgetRepository;
	@GetMapping("/api/paragraph/widget/{wid}")
	public Widget findWidgetById(@PathVariable("wid") Integer widgetId){
		return paragraphWidgetRepository.findById(widgetId).get();
	}
	
	@PutMapping("/api/paragraph/widget/{wid}")
	public Widget updateWidget(@PathVariable("wid") Integer widgetId,
			@RequestBody Widget widget){
		ParagraphWidget w = null;
		try {
			w = paragraphWidgetRepository.findById(widgetId).get();
		} catch (NoSuchElementException ex) {
			if (w == null) {
				w = new ParagraphWidget();
				Widget widg = widgetRepository.findById(widgetId).get();
				w.getParagraphWidget(widg);
				widgetRepository.deleteById(widgetId);
			}
		}
		w.set(widget);
		return paragraphWidgetRepository.save(w);
	}
	
	@DeleteMapping("/api/paragraph/widget/{wid}")
	public void deleteWidget(@PathVariable("wid") Integer widgetId){
		paragraphWidgetRepository.deleteById(widgetId);
	}
}
