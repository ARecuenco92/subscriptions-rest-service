package com.arecuenco.subscription;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionServiceTest {

	@Autowired
	SubscriptionService service;
	
	@Test
	public void nullSubscription() {
		Subscription subscription = null;
		
		Integer result = service.subscribe(subscription);
		assertNull(result);
	}
	
	@Test
	public void unconsentedSubscription() {
		Subscription subscription = new Subscription();
		subscription.setConsent(false);
		
		Integer result = service.subscribe(subscription);
		assertNull(result);
	}

	@Test
	public void correctSubscription() {
		Subscription subscription = new Subscription();
		subscription.setEmail("user@email.com");
		subscription.setDateOfBirth(new Date());
		subscription.setConsent(true);
		
		Integer result = service.subscribe(subscription);
		assertNotNull(result);
	}
}
