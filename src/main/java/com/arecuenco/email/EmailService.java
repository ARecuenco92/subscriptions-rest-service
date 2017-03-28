package com.arecuenco.email;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arecuenco.email.entity.Email;
import com.arecuenco.subscription.entity.Subscription;

@Service
public class EmailService {

	/**
	 * Send an email to all subscribers
	 */
	public boolean sendEmail(Email email, List<Subscription> subscriptions) {
		return true;
	}

}
