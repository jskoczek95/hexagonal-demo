package com.hexagonal.infrastructure.bidding;

import com.hexagonal.domain.bidding.port.in.BidStore;

import java.util.HashMap;
import java.util.Map;

public class InMemoryBidStore implements BidStore {

    Map<Long, BidDto> memory = new HashMap<>();

    @Override
    public void saveBid(BidDto bidDto) {
        memory.put(bidDto.getId(), bidDto);
        System.out.println("Saved bid with id: " + bidDto.getId());
    }

    @Override
    public BidDto loadBid(Long bidId) {
        return memory.get(bidId);
    }
}
