package com.hexagonal.domain.bidding.events;

import com.hexagonal.commons.AggregateId;
import com.hexagonal.commons.events.Event;
import com.hexagonal.domain.bidding.Bid;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class BidCreatedEvent implements Event<Bid> {

    private final AggregateId id;
    private final LocalDateTime eventDate;

    public BidCreatedEvent(AggregateId id, LocalDateTime eventDate) {
        this.id = id;
        this.eventDate = eventDate;
    }

    @Override
    public AggregateId getAggregateId() {
        return id;
    }

    @Override
    public int getVersion() {
        return new AtomicInteger(0).getAndIncrement();
    }

    @Override
    public String getEventType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LocalDateTime getDate() {
        return eventDate;
    }
}
