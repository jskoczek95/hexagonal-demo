package com.hexagonal.domain.bidding;

import com.hexagonal.domain.bidding.port.in.BidStore;
import com.hexagonal.domain.bidding.port.out.BiddingService;
import com.hexagonal.domain.bidding.port.shared.Money;
import com.hexagonal.domain.bidding.port.shared.Offer;
import lombok.RequiredArgsConstructor;

import java.util.logging.Logger;

@RequiredArgsConstructor
class BiddingServiceImpl implements BiddingService {

    //sl4fj from lombok doesnt work; look into it later
    private static final Logger LOGGER = Logger.getLogger(BiddingServiceImpl.class.getName());

    private final BidStore bidStore;

    @Override
    public BidId createBid(String title, String description, Money startingOffer) {
        Bid bid = BiddingFactory.getInstance().createBid(title, description, startingOffer);
        bid.onCreate();
        bidStore.saveBid(BiddingFactory.getInstance().toSaveBid(bid));
        return bid.getBidId();
    }

    @Override
    public void placeOffer(BidId bidId, Offer offer) {
        Bid bid = BiddingFactory.getInstance().toDomain(bidStore.loadBid(bidId));
        bid.addOffer(offer);
        bidStore.saveBid(BiddingFactory.getInstance().toSaveBid(bid));
    }
}
