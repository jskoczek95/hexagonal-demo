package com.hexagonal.domain.bidding;

import com.hexagonal.commons.AggregateId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode
@ToString
public class BidId implements AggregateId {

    private final String id;

    private BidId(String id) {
        this.id = id;
    }

    public static BidId generateId() {
        return new BidId(UUID.randomUUID().toString());
    }

    public String value() {
        return id;
    }
}
