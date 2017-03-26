package com.arecuenco.email;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arecuenco.subscription.Subscription;

@Service
public class EmailService {

	/**
	 * Send an email to all subscribers
	 * 
	 * @param email
	 * @param subscriptions
	 * @return
	 */
	public boolean sendEmail(Email email, List<Subscription> subscriptions) {
		return true;
	}

}
