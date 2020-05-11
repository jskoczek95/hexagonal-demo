package com.hexagonal.domain.bidding;

import com.hexagonal.domain.bidding.port.in.BidStore;
import com.hexagonal.domain.bidding.port.out.BiddingService;
import com.hexagonal.domain.bidding.port.shared.Offer;
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
        BidStore bidStore = new InMemoryTestBidStore();
        BiddingFacade biddingFacade = new BiddingFacade(bidStore);

        BiddingService biddingService = biddingFacade.getBiddingService();
        Long bidId = biddingService.createBid("Sample title", "Sample description", BigDecimal.valueOf(25.20));

        BidStore.BidDto bidDto = bidStore.loadBid(bidId);
        Assert.assertNotNull(bidDto);
    }

    @Test
    void placeOffer() {
        BidStore bidStore = new InMemoryTestBidStore();
        BiddingFacade biddingFacade = new BiddingFacade(bidStore);
        BiddingService biddingService = biddingFacade.getBiddingService();
        biddingService.createBid("Sample title", "Sample description", BigDecimal.valueOf(25.20));
        Offer offer = new Offer(5L, BigDecimal.valueOf(30));

        biddingService.placeOffer(1L, offer);
    }

    private static class InMemoryTestBidStore implements BidStore {
        private final Map<Long, BidDto> memory = new HashMap<>();
        private static final Logger LOGGER = Logger.getLogger(InMemoryTestBidStore.class.getName());

        @Override
        public void saveBid(BidDto bidDto) {
            memory.put(bidDto.getId(), bidDto);
            LOGGER.info("Saved bid with id: " + bidDto.getId());
            LOGGER.info("Current offers are: " + Arrays.toString(bidDto.getOffers().toArray()));
        }

        @Override
        public BidDto loadBid(Long bidId) {
            return memory.get(bidId);
        }

    }

}