package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication
public class EventServiceApplication {

	public static void main(String[] args) {
		log.info("Start - ");
		SpringApplication.run(EventServiceApplication.class, args);
	}

}
