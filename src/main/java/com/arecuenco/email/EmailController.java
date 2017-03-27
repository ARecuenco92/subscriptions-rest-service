package com.arecuenco.email;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arecuenco.subscription.Subscription;
import com.arecuenco.subscription.SubscriptionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/email")
public class EmailController {

	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private EmailService emailService;

	@ApiOperation(value = "Send email to the newsletter subscribers")
	@RequestMapping(value = "/{newsletterId}", method = RequestMethod.POST)
	public ResponseEntity<EmailResponse> sendNewsletterEmails(@PathVariable Integer newsletterId, @RequestBody Email email) {
		EmailResponse response = new EmailResponse();
		List<Subscription> list = subscriptionService.getSubscriptions(newsletterId);
		
		boolean sent = emailService.sendEmail(email, list);
		response.setSent(sent);
		
		return ResponseEntity.ok().body(response);
	}
	
	private class EmailResponse {
		private boolean sent;

		@SuppressWarnings("unused")
		public boolean isSent() {
			return sent;
		}

		public void setSent(boolean sent) {
			this.sent = sent;
		}

	}
}
