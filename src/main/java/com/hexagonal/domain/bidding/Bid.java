package com.hexagonal.domain.bidding;

import com.hexagonal.domain.bidding.port.shared.Offer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Getter
class Bid {

    private Long id;
    private String title;
    private String description;
    private BigDecimal startingOffer;
    private List<Offer> offers;

    void addOffer(Offer offer) {
        offers.add(offer);
    }
}
