package com.hexagonal.domain.bidding.port.out;

import com.hexagonal.domain.bidding.BidId;
import com.hexagonal.domain.bidding.port.shared.Money;
import com.hexagonal.domain.bidding.port.shared.Offer;

public interface BiddingService {

    BidId createBid(String title, String description, Money startingOffer);

    void placeOffer(BidId bidId, Offer offer);
}
