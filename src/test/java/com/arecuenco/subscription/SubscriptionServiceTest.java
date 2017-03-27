package com.arecuenco.subscription;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Date;

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
public class SubscriptionServiceTest {

	@InjectMocks
	private SubscriptionService service;
	
	@Mock
	private SubscriptionRepository repository;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testNullSubscription() {
		Subscription subscription = null;
		
		assertNull(service.subscribe(subscription));
	}
	
	@Test
	public void testUnconsentedSubscription() {
		Subscription subscription = new Subscription();
		subscription.setConsent(false);
		
		Integer result = service.subscribe(subscription);
		assertNull(result);
	}

	@Test
	public void testValidSubscription() {
		Subscription subscription = new Subscription();
		subscription.setId(1);
		subscription.setNewsletterId(212);
		subscription.setEmail("user@email.com");
		subscription.setDateOfBirth(new Date());
		subscription.setConsent(true);
		
		when(repository.save(subscription)).thenReturn(subscription);
		
		Integer result = service.subscribe(subscription);
		assertNotNull(result);
	}
}
