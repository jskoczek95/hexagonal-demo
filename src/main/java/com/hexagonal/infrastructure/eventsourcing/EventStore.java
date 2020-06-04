package com.hexagonal.infrastructure.eventsourcing;

import com.hexagonal.commons.AggregateId;
import com.hexagonal.commons.events.Event;

import java.util.List;

public interface EventStore<T> {

    void store(AggregateId aggregateId, List<Event<T>> events);
}
