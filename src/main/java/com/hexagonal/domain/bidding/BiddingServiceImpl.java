package com.hexagonal.domain.bidding;

import com.hexagonal.domain.bidding.port.in.BidStore;
import com.hexagonal.domain.bidding.port.out.BiddingService;
import com.hexagonal.domain.bidding.port.shared.Offer;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
class BiddingServiceImpl implements BiddingService {

    private final BidStore bidStore;

    @Override
    public Long createBid(String title, String description, BigDecimal startingOffer) {
        Bid bid = BiddingFactory.getInstance().createBid(title, description, startingOffer);
        bidStore.saveBid(BiddingFactory.getInstance().toSaveBid(bid));
        return bid.getId();
    }

    @Override
    public void placeOffer(Long bidId, Offer offer) {
        Bid bid = BiddingFactory.getInstance().toDomain(bidStore.loadBid(bidId));
        bid.addOffer(offer);
        bidStore.saveBid(BiddingFactory.getInstance().toSaveBid(bid));
    }
}
