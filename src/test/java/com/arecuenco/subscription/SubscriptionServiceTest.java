package com.arecuenco.subscription;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionServiceTest {

	@Autowired
	private SubscriptionService service;
	
	@Mock
	private SubscriptionRepository repository;
	
	@Test
	public void testNullSubscription() {
		Subscription subscription = null;
		
		Integer result = service.subscribe(subscription);
		assertNull(result);
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
		subscription.setNewsletterId(212);
		subscription.setEmail("user@email.com");
		subscription.setDateOfBirth(new Date());
		subscription.setConsent(true);
		
		Integer result = service.subscribe(subscription);
		assertNotNull(result);
	}
}
