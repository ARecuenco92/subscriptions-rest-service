package com.arecuenco.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arecuenco.event.entity.Event;
import com.arecuenco.event.entity.EventResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Event Api")
@RestController
@RequestMapping(path = "/api/event")
public class EventController {

	@Autowired
	private EventService eventService;

	@ApiOperation(value = "Find and return all newsletters")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<Event>> getAll() {
		return ResponseEntity.ok().body(eventService.getAll());
	}

	@ApiOperation(value = "Create newsletter and return its identifier")
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

	@ApiOperation(value = "Find newsletter by identifier and return it")
	@RequestMapping(value = "/{newsletterId}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent(@PathVariable Integer newsletterId) {
		Event event = eventService.getEvent(newsletterId);
		if (event != null) {
			return ResponseEntity.ok().body(event);
		}
		return ResponseEntity.notFound().build();
	}

	private boolean isValidRequest(Event event) {
		return event.hasTopic();
	}

}
