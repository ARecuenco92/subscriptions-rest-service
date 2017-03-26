package com.arecuenco.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arecuenco.event.Event;
import com.arecuenco.event.EventService;

@RestController
@RequestMapping(path = "/subscription")
public class SubscriptionController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private SubscriptionService subscriptionService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SubscriptionResponse> subscribe(@RequestBody Subscription subscription) {
		if (isValidRequest(subscription)) {
			Integer id = subscriptionService.subscribe(subscription);
			SubscriptionResponse response = new SubscriptionResponse();
			response.setId(id);

			return ResponseEntity.ok().body(response);
		}
		return ResponseEntity.badRequest().build();
	}

	private boolean isValidRequest(Subscription subscription){
		return isValidSubscription(subscription) && existsNewsletter(subscription);
	}
	
	private boolean isValidSubscription(Subscription subscription) {
		return subscription.hasNewsletterId() && subscription.hasEmail() && subscription.hasDateOfBirth();
	} 
	
	private boolean existsNewsletter(Subscription subscription) {
		Event event = eventService.getEvent(subscription.getNewsletterId());
		return event != null;
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
