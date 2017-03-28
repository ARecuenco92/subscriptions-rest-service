package com.arecuenco.event;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.arecuenco.event.entity.Event;
import com.arecuenco.utils.TestUtils;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class EventControllerTest {

	@Mock
	private EventService service;

	@InjectMocks
	private EventController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testGetAll() throws Exception {
		MockHttpServletRequestBuilder request = get("/api/event");

		mockMvc.perform(request).andExpect(status().isOk());
	}
	
	@Test
	public void testgetEventNotFound() throws Exception {
		MockHttpServletRequestBuilder request = get("/api/event/1");

		mockMvc.perform(request).andExpect(status().isNotFound());
	}
	
	@Test
	public void testGetEvent() throws Exception {
		Event event = new Event();
		event.setId(1);
		event.setTopic("Newsletter topic");
			
		when(service.getEvent(event.getId())).thenReturn(event);

		MockHttpServletRequestBuilder request = get("/api/event/1");
		
		mockMvc.perform(request).andExpect(status().isOk());
	}

	@Test
	public void testCreateEventBadRequest() throws Exception {
		Event event = new Event();
		String json = TestUtils.toJSON(event);

		when(service.createEvent(event)).thenReturn(1);

		MockHttpServletRequestBuilder request = post("/api/event").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json);

		mockMvc.perform(request).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCreateEventValidRequest() throws Exception {
		Event event = new Event();
		event.setTopic("Newsletter topic");

		String json = TestUtils.toJSON(event);

		when(service.createEvent(event)).thenReturn(1);

		MockHttpServletRequestBuilder request = post("/api/event").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json);

		mockMvc.perform(request).andExpect(status().isOk());
	}

}
