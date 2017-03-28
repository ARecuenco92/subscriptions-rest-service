package com.arecuenco.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arecuenco.event.EventService;
import com.arecuenco.event.entity.Event;
import com.arecuenco.subscription.entity.Subscription;
import com.arecuenco.subscription.entity.SubscriptionResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Subscription Api")
@RestController
@RequestMapping(path = "/api/subscription")
public class SubscriptionController {

	@Autowired
	private EventService eventService;

	@Autowired
	private SubscriptionService subscriptionService;

	@ApiOperation(value = "Create subscription and return its identifier")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SubscriptionResponse> subscribe(@RequestBody Subscription subscription) {
		if (isValidSubscription(subscription)) {
			if (existsNewsletter(subscription)) {
				Integer id = subscriptionService.subscribe(subscription);
				SubscriptionResponse response = new SubscriptionResponse();
				response.setId(id);

				return ResponseEntity.ok().body(response);
			}
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.badRequest().build();
	}

	private boolean isValidSubscription(Subscription subscription) {
		return subscription.hasNewsletterId() && subscription.hasEmail() && subscription.hasDateOfBirth();
	}

	private boolean existsNewsletter(Subscription subscription) {
		Event event = eventService.getEvent(subscription.getNewsletterId());
		return event != null;
	}

}
