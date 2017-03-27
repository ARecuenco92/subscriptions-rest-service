package com.arecuenco.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	public Iterable<Event> getAll() {
		return repository.findAll();
	}

	public Event getEvent(Integer newsletterId) {
		return repository.findOne(newsletterId);
	}
	
	public Integer createEvent(Event event){
		Event result = repository.save(event);
		return result.getId();
	}
}
