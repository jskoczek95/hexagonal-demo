package com.hexagonal.domain.bidding;

import com.hexagonal.domain.bidding.port.in.BidStore;
import com.hexagonal.domain.bidding.port.out.BiddingService;
import com.hexagonal.domain.bidding.port.shared.Offer;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.logging.Logger;

@RequiredArgsConstructor
class BiddingServiceImpl implements BiddingService {

    //sl4fj from lombok doesnt work; look into it later
    private static final Logger LOGGER = Logger.getLogger(BiddingServiceImpl.class.getName());

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
        LOGGER.info(String.format("User with id: %d has placed an offer on bid with id: %d", offer.getUserId(), bidId));
    }
}
