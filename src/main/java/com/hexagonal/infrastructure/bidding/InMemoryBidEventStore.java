package com.hexagonal.infrastructure.bidding;

import com.hexagonal.commons.AggregateId;
import com.hexagonal.commons.events.Event;
import com.hexagonal.infrastructure.eventsourcing.EventStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InMemoryBidEventStore<T> implements EventStore<T> {

    Map<AggregateId, List<Event<T>>> memoryEventStore = new HashMap<>();

    @Override
    public void store(AggregateId aggregateId, List<Event<T>> events) {
        memoryEventStore.put(aggregateId, events);
        System.out.println("Stored aggregate: " + aggregateId);
        events.forEach(event -> System.out.println(event.getEventType()));
        //TODO fix lombok loggers
    }
}
