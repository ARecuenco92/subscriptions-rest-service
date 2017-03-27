package com.arecuenco.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {

	@InjectMocks
	private EventService service;
	
	@Mock
	private EventRepository repository;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAll() {
		List<Event> events = new ArrayList<Event>();
		events.add(new Event());
		
		when(repository.findAll()).thenReturn(events);
		assertNotNull(service.getAll());
		assertEquals(1, events.size());
	}
	
	@Test
	public void testGetEvent() {
		Event event = new Event();
		event.setId(1);
		event.setTopic("Newslleter");
				
		when(repository.findOne(event.getId())).thenReturn(event);
				
		Event result = service.getEvent(event.getId());
		assertNotNull(result);
		assertEquals(result.getId(), event.getId());
		assertEquals(result.getTopic(), event.getTopic());
	}
	
	@Test
	public void testCreateEvent() {
		Event event = new Event();
		event.setId(1);
		event.setTopic("Newslleter");
		
		when(repository.save(event)).thenReturn(event);
		
		Integer result = service.createEvent(event);
		assertNotNull(result);
	}
}
