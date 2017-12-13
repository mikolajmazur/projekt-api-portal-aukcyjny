package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.AuctionMiniDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CreateAuctionDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.AuctionOffer;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.AuctionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuctionServiceV2 {
    private final AuctionRepository auctionRepository;

    public Auction getAuction(long id) {
        return auctionRepository.findOne(id);
    }

    public AuctionOffer addOfferToAuction(Auction auction, AuctionOffer offer) {
        offer.setSubmissionDate(LocalDateTime.now());

        auction.addOffer(offer);
        auctionRepository.save(auction);

        return offer;
    }

    public Auction createAuction(CreateAuctionDTO dto){
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(dto.getDuration());
        Auction auction = Auction.builder()
                .description(dto.getDescription())
                .startDateTime(startDate)
                .endDateTime(endDate)
                .photoUrl("")
                .startingPrice(dto.getStartingPrice())
                .title(dto.getTitle())
                .build();

        auctionRepository.save(auction);
        return auction;
    }

    public List<AuctionMiniDTO> getAll() {
        List<AuctionMiniDTO> list = new ArrayList<>();
        auctionRepository.findAll().forEach(a -> list.add(
                AuctionMiniDTO.builder()
                        .id(a.getId())
                        .title(a.getTitle())
                        .price(a.getCurrentPrice())
                        .build()
        ));

        return list;
    }
}
