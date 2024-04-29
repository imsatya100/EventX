package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Event;
import com.example.demo.service.EventService;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
	 @Autowired
	 private EventService eventService;
	 
	 // Create
	 @PostMapping
	 public ResponseEntity<Event> createEvent(@RequestBody Event event) {
		 return ResponseEntity.ok(eventService.createEvent(event));
	 }

	 // Read
	 @GetMapping
	 public ResponseEntity<List<Event>> getAllEvents() {
		 return ResponseEntity.ok(eventService.getAllEvents());
	 }

	 @GetMapping("/{id}")
	 public ResponseEntity<Event> getEventById(@PathVariable Long id) {
		 return eventService.getEventById(id)
				 .map(ResponseEntity::ok)
				 .orElse(ResponseEntity.notFound().build());
	 }

	 // Update
	 @PutMapping("/{id}")
	 public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
		 return ResponseEntity.ok(eventService.updateEvent(id, eventDetails));
	 }

	 // Delete
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
		 eventService.deleteEvent(id);
		 return ResponseEntity.ok().build();
	 }
}
