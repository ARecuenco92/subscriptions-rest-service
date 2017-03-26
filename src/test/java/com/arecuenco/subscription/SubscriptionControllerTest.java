package com.arecuenco.subscription;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class SubscriptionControllerTest {

	@Mock
	private SubscriptionService service;

	@InjectMocks
	private SubscriptionController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testSubscriptionEmptyRequest() throws Exception {
		mockMvc.perform(post("/subscription")).andExpect(status().isBadRequest());
	}

	@Test
	public void testSubscriptionBadRequest() throws Exception {
		Subscription subscription = new Subscription();
		subscription.setId(1);
		subscription.setDateOfBirth(new Date());
		subscription.setConsent(true);

		String json = toJSON(subscription);

		MockHttpServletRequestBuilder request = post("/subscription").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json);

		mockMvc.perform(request).andExpect(status().isBadRequest());
	}

	@Test
	public void testSubscriptionCorrectdRequest() throws Exception {
		Subscription subscription = new Subscription();
		subscription.setNewsletterId(132);
		subscription.setEmail("eamil");
		subscription.setDateOfBirth(new Date());
		subscription.setConsent(true);

		String json = toJSON(subscription);

		when(service.subscribe(subscription)).thenReturn(1);

		MockHttpServletRequestBuilder request = post("/subscription").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json);

		mockMvc.perform(request).andExpect(status().isOk());
	}

	private static String toJSON(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsString(object);
	}
}
