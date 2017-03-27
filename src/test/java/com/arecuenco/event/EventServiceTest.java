package com.arecuenco.event;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {

	@Autowired
	private EventService service;
	
	@Mock
	private EventRepository repository;
	
	@Test
	public void testGetAll() {
		Iterable<Event> events = new ArrayList<Event>();
		((ArrayList<Event>) events).add(new Event());
		
		when(repository.findAll()).thenReturn(events);
		assertNotNull(service.getAll());
		//assetEquals(1, events.iterator()());
	}
	
}
