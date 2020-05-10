package com.hexagonal.domain.bidding.port.in;

import com.hexagonal.domain.bidding.port.shared.Offer;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

public interface BidStore {

    void saveBid(BidDto bidDto);

    BidDto loadBid(Long bidId);

    @Value
    @Builder
    class BidDto {
        Long id;
        String description;
        String title;
        BigDecimal startingOffer;
        List<Offer> offers;
    }
}
