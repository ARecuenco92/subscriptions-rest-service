package com.arecuenco.event;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arecuenco.event.entity.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

	@Cacheable("eventCache")
	public Iterable<Event> findAll();
	
	@Cacheable("eventCache")
	public Event findOne(Integer id);
	
	@CacheEvict("eventCache")
	public Event save(Event subscription);
}
