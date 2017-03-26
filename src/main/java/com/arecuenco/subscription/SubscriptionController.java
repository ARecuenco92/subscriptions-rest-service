package com.arecuenco.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/subscription")
public class SubscriptionController {

	@Autowired
	SubscriptionService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SubscriptionResponse> subscribe(@RequestBody Subscription subscription) {
		if (isValidPostRequest(subscription)) {
			Integer id = service.subscribe(subscription);
			SubscriptionResponse response = new SubscriptionResponse();
			response.setId(id);

			return ResponseEntity.ok().body(response);
		}
		return ResponseEntity.badRequest().build();
	}

	private boolean isValidPostRequest(Subscription subscription) {
		return subscription.hasNewsletterId() && subscription.hasEmail() && subscription.hasDateOfBirth();
	}

	private class SubscriptionResponse {
		private Integer id;

		@SuppressWarnings("unused")
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

	}

}
