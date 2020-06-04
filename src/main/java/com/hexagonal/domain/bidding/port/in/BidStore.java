package com.hexagonal.domain.bidding.port.in;

import com.hexagonal.commons.events.Event;
import com.hexagonal.domain.bidding.Bid;
import com.hexagonal.domain.bidding.BidId;
import com.hexagonal.domain.bidding.port.shared.Money;
import com.hexagonal.domain.bidding.port.shared.Offer;
import lombok.Builder;
import lombok.Value;

import java.util.List;

public interface BidStore {

    void saveBid(BidDto bidDto);

    BidDto loadBid(BidId bidId);

    @Value
    @Builder
    class BidDto {
        BidId bidId;
        String description;
        String title;
        Money startingOffer;
        List<Offer> offers;
        List<Event<Bid>> bidChanges;
    }
}
