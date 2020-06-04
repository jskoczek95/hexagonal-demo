package com.hexagonal.domain.bidding;

import com.hexagonal.domain.bidding.port.shared.Money;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Collections;

import static com.hexagonal.domain.bidding.port.in.BidStore.BidDto;

class BiddingFactory {

    @Getter
    private static final BiddingFactory instance = new BiddingFactory();

    private final BidMapper mapper = Mappers.getMapper(BidMapper.class);

    Bid createBid(String title, String description, Money startingOffer) {
        return new Bid(BidId.generateId(), title, description, startingOffer, Collections.emptyList());
    }

    BidDto toSaveBid(Bid bid) {
        return BidDto.builder().bidId(bid.getBidId())
                .bidChanges(bid.getBidChanges())
                .description(bid.getDescription())
                .offers(bid.getOffers())
                .startingOffer(bid.getStartingOffer())
                .title(bid.getTitle()).build();
    }

    Bid toDomain(BidDto bidDto) {
        return mapper.toDomain(bidDto);
    }

    @Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
    interface BidMapper {
//        TODO: Get rid of mapstruct?

        Bid toDomain(BidDto bidDto);
    }
}
