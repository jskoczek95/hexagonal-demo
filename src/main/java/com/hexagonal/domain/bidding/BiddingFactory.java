package com.hexagonal.domain.bidding;

import lombok.Getter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

import static com.hexagonal.domain.bidding.port.in.BidStore.BidDto;

class BiddingFactory {

    @Getter
    private static final BiddingFactory instance = new BiddingFactory();
    private static final AtomicLong SEQUENCE = new AtomicLong();

    private final BidMapper mapper = Mappers.getMapper(BidMapper.class);

    Bid createBid(String title, String description, BigDecimal startingOffer) {
        return new Bid(SEQUENCE.incrementAndGet(), title, description, startingOffer, Collections.emptyList());
    }

    BidDto toSaveBid(Bid bid) {
        return mapper.toSaveBid(bid);
    }

    Bid toDomain(BidDto bidDto) {
        return mapper.toDomain(bidDto);
    }

    @Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
    interface BidMapper {
        BidDto toSaveBid(Bid bid);

        Bid toDomain(BidDto bidDto);
    }
}
