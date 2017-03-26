package com.arecuenco.subscription;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

	public List<Subscription> findByNewsletterId(Integer newsletterId);
}
