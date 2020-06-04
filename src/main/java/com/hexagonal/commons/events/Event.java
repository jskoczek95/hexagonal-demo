package com.hexagonal.commons.events;

import com.hexagonal.commons.AggregateId;

import java.time.LocalDateTime;

public interface Event<T> {

    AggregateId getAggregateId();

    int getVersion();

    String getEventType();

    LocalDateTime getDate();
}
