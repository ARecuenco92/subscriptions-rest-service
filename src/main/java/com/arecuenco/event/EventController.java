package com.arecuenco.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/event")
public class EventController {

	@Autowired
	private EventService eventService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<Event>> getAll() {
		return ResponseEntity.ok().body(eventService.getAll());
	}
	
	@RequestMapping(value = "/{newsletterId}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent(@PathVariable Integer newsletterId) {
		Event event = eventService.getEvent(newsletterId);
		if(event != null){
			return ResponseEntity.ok().body(event);
		} 
		return ResponseEntity.notFound().build();
	}
}
