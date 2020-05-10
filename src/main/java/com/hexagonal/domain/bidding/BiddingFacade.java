package com.hexagonal.domain.bidding;


import com.hexagonal.domain.bidding.port.in.BidStore;
import com.hexagonal.domain.bidding.port.out.BiddingService;
import lombok.Getter;

@Getter
public class BiddingFacade {

    private final BiddingService biddingService;

    public BiddingFacade(BidStore bidStore) {
        biddingService = new BiddingServiceImpl(bidStore);
    }
}
