package com.hexagonal.infrastructure.bidding;

import com.hexagonal.commons.AggregateId;
import com.hexagonal.domain.bidding.Bid;
import com.hexagonal.domain.bidding.BidId;
import com.hexagonal.domain.bidding.port.in.BidStore;
import com.hexagonal.infrastructure.eventsourcing.EventStore;

import java.util.HashMap;
import java.util.Map;

public class InMemoryBidStore implements BidStore {

    Map<AggregateId, BidDto> memory = new HashMap<>();
    private final EventStore<Bid> eventStore = new InMemoryBidEventStore<>();

    @Override
    public void saveBid(BidDto bidDto) {
        memory.put(bidDto.getBidId(), bidDto);
        eventStore.store(bidDto.getBidId(), bidDto.getBidChanges());
        //TODO fix lombok logger
        System.out.println("Saved bid with id: " + bidDto.getBidId());
    }

    @Override
    public BidDto loadBid(BidId bidId) {
        return memory.get(bidId);
    }
}
