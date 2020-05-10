package com.hexagonal.domain.bidding.port.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Offer {

    private Long userId;
    private BigDecimal userOffer;
}
