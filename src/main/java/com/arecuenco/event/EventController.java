package com.arecuenco.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/event")
public class EventController {

	@Autowired
	private EventService eventService;

	@ApiOperation(value = "Find and return all newsletters")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<Event>> getAll() {
		return ResponseEntity.ok().body(eventService.getAll());
	}
	
	@ApiOperation(value = "Find newsletter by identifier and return it")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<EventResponse> createEvent(@RequestBody Event event) {
		if (isValidRequest(event)) {
			Integer id = eventService.createEvent(event);
			EventResponse response = new EventResponse();
			response.setId(id);
	
			return ResponseEntity.ok().body(response);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation(value = "Create newsletter and return its identifier")
	@RequestMapping(value = "/{newsletterId}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent(@PathVariable Integer newsletterId) {
		Event event = eventService.getEvent(newsletterId);
		if(event != null){
			return ResponseEntity.ok().body(event);
		} 
		return ResponseEntity.notFound().build();
	}
	
	private boolean isValidRequest(Event event){
		return event.hasTopic();
	}
	
	private class EventResponse {
		private Integer id;

		@SuppressWarnings("unused")
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

	}
}
