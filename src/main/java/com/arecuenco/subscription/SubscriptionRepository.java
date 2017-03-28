package com.arecuenco.subscription;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arecuenco.subscription.entity.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

	@Cacheable("subscriptionCache")
	public List<Subscription> findByNewsletterId(Integer newsletterId);

	@CacheEvict("subscriptionCache")
	public Subscription save(Subscription subscription);
}
