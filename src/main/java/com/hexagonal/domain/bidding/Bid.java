package com.hexagonal.domain.bidding;

import com.hexagonal.commons.events.Event;
import com.hexagonal.domain.bidding.events.BidCreatedEvent;
import com.hexagonal.domain.bidding.port.shared.Money;
import com.hexagonal.domain.bidding.port.shared.Offer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Getter
public class Bid {

    private BidId bidId;
    private String title;
    private String description;
    private Money startingOffer;
    private List<Offer> offers;
    private List<Event<Bid>> bidChanges = new ArrayList<>();

    public Bid(BidId bidId, String title, String description, Money startingOffer, List<Offer> offers) {
        this.bidId = bidId;
        this.title = title;
        this.description = description;
        this.startingOffer = startingOffer;
        this.offers = offers;
    }

    void addOffer(Offer offer) {
        offers.add(offer);
    }

    List<Event<Bid>> getBidChanges() {
        return new ArrayList<>(bidChanges);
    }

    void onCreate() {
        bidChanges.add(new BidCreatedEvent(bidId, LocalDateTime.now()));
    }

}
