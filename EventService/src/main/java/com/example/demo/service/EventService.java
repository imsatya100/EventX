package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;

@Service
public class EventService {
	@Autowired
    private EventRepository eventRepository;

    // Create operation
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Read operation
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // Update operation
    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        event.setEventName(eventDetails.getEventName());
        event.setEventDescription(eventDetails.getEventDescription());
        event.setCreatedDate(eventDetails.getCreatedDate());
        event.setModifiedDate(eventDetails.getModifiedDate());
        return eventRepository.save(event);
    }

    // Delete operation
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
