package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Event;

public interface EventRepository extends CrudRepository<Event, Long>{

}
