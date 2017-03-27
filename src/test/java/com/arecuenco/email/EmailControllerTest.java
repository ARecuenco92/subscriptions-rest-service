package com.arecuenco.email;

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

import com.arecuenco.subscription.SubscriptionService;
import com.arecuenco.utils.TestUtils;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class EmailControllerTest {

	@Mock
	private SubscriptionService subscriptionService;

	@Mock
	private EmailService emailService;

	@InjectMocks
	private EmailController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testSendMail() throws Exception {
		Email email = new Email();
		email.setSubject("Subject");
		email.setBody("Body");
		
		String json = TestUtils.toJSON(email);

		MockHttpServletRequestBuilder request = post("/email/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json);

		mockMvc.perform(request).andExpect(status().isOk());
	}
	
	
}
