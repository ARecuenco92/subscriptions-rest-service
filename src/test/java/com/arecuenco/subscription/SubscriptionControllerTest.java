package com.arecuenco.subscription;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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

import com.arecuenco.event.Event;
import com.arecuenco.event.EventService;
import com.arecuenco.utils.TestUtils;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class SubscriptionControllerTest {

	@Mock
	private EventService eventService;
	
	@Mock
	private SubscriptionService subscriptionService;

	@InjectMocks
	private SubscriptionController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testSubscribeBadRequest() throws Exception {
		Subscription subscription = new Subscription();
		subscription.setNewsletterId(1);
		subscription.setDateOfBirth(new Date());
		subscription.setConsent(true);

		String json = TestUtils.toJSON(subscription);

		MockHttpServletRequestBuilder request = post("/api/subscription")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json);

		mockMvc.perform(request).andExpect(status().isBadRequest());
	}

	@Test
	public void testSubscribeNotFoundRequest() throws Exception {
		Subscription subscription = new Subscription();
		subscription.setNewsletterId(1);
		subscription.setEmail("email");
		subscription.setDateOfBirth(new Date());
		subscription.setConsent(true);

		String json = TestUtils.toJSON(subscription);

		when(subscriptionService.subscribe(subscription)).thenReturn(1);

		MockHttpServletRequestBuilder request = post("/api/subscription")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json);

		mockMvc.perform(request).andExpect(status().isNotFound());
	}
	
	@Test
	public void testSubscribeValidRequest() throws Exception {
		Subscription subscription = new Subscription();
		subscription.setNewsletterId(1);
		subscription.setEmail("email");
		subscription.setDateOfBirth(new Date());
		subscription.setConsent(true);

		String json = TestUtils.toJSON(subscription);

		when(eventService.getEvent(subscription.getNewsletterId())).thenReturn(new Event());
		when(subscriptionService.subscribe(subscription)).thenReturn(1);

		MockHttpServletRequestBuilder request = post("/api/subscription")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json);

		mockMvc.perform(request).andExpect(status().isOk());
	}

	
}
