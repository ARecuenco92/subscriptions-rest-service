package com.arecuenco.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository repository;

	public Integer subscribe(Subscription subscription) {
		Integer index = null;
		if (validSubscription(subscription)) {
			Subscription result = repository.save(subscription);
			return result.getId();
		}
		return index;
	}

	private boolean validSubscription(Subscription subscription) {
		return subscription != null && subscription.isConsent();
	}

}
