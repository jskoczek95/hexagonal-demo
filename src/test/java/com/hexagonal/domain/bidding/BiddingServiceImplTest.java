package com.hexagonal.domain.bidding;

import com.hexagonal.domain.bidding.port.in.BidStore;
import com.hexagonal.domain.bidding.port.out.BiddingService;
import com.hexagonal.domain.bidding.port.shared.Money;
import com.hexagonal.domain.bidding.port.shared.Offer;
import com.hexagonal.infrastructure.bidding.InMemoryBidStore;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

class BiddingServiceImplTest {

    @Test
    void createBid() {
        BidStore bidStore = new InMemoryBidStore();
        BiddingFacade biddingFacade = new BiddingFacade(bidStore);

        BiddingService biddingService = biddingFacade.getBiddingService();
        BidId bidId = biddingService.createBid("Sample title", "Sample description", new Money(25));

        BidStore.BidDto bidDto = bidStore.loadBid(bidId);
        Assert.assertNotNull(bidDto);
    }

    @Test
    void placeOffer() {
        BidStore bidStore = new InMemoryTestBidStore();
        BiddingFacade biddingFacade = new BiddingFacade(bidStore);
        BiddingService biddingService = biddingFacade.getBiddingService();
        BidId bidId = biddingService.createBid("Sample title", "Sample description", new Money(25));
        Offer offer = new Offer(5L, BigDecimal.valueOf(30));

        biddingService.placeOffer(bidId, offer);
    }

    private static class InMemoryTestBidStore implements BidStore {
        private final Map<BidId, BidDto> memory = new HashMap<>();
        private static final Logger LOGGER = Logger.getLogger(InMemoryTestBidStore.class.getName());

        @Override
        public void saveBid(BidDto bidDto) {
            memory.put(bidDto.getBidId(), bidDto);
            LOGGER.info("Saved bid with id: " + bidDto.getBidId().value());
            LOGGER.info("Current offers are: " + Arrays.toString(bidDto.getOffers().toArray()));
        }

        @Override
        public BidDto loadBid(BidId bidId) {
            return memory.get(bidId);
        }

    }


}