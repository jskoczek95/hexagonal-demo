package com.hexagonal.domain.bidding.port.out;

import com.hexagonal.domain.bidding.port.shared.Offer;

import java.math.BigDecimal;

public interface BiddingService {

    Long createBid(String title, String description, BigDecimal startingOffer);

    void placeOffer(Long bidId, Offer offer);
}
