package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.*;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.AuctionOffer;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.AuctionNotFoundException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.AuctionServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuctionControllerv2 {
    private final AuctionServiceImpl auctionService;

    @GetMapping("/auctions")
    public List<AuctionMiniDTO> getAuctionsList() {
        return auctionService.getAll();
    }

    @GetMapping("/auctions/{id}")
    public AuctionDTO getAuction(@PathVariable long id){
        val auction = auctionService.getAuction(id);
        if (auction == null){
            throw new AuctionNotFoundException(String.valueOf(id));
        }
        return createAuctionDTO(auction);
    }

    private AuctionDTO createAuctionDTO(Auction auction){
        val dto = AuctionDTO.builder()
                .id(auction.getId())
                .title(auction.getTitle())
                .description(auction.getDescription())
                .currentPrice(auction.getCurrentPrice())
                .minimalBid(auction.getMinimalBid())
                .photoUrl(auction.getPhotoUrl())
                .startDateTime(auction.getStartDateTime())
                .endDateTime(auction.getEndDateTime())
                .sellerId(auction.getOwner().getId())
                .sellerUsername(auction.getOwner().getUsername())
                .build();

        // add categories that contatin this auction
        List<CategoryDTO> categories = dto.getContainingCategories();
        Category category = auction.getCategory();
        do {
            categories.add(CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build());
            category = category.getParent();

        } while (category != null);
        Collections.reverse(categories);

        // add offers
        List<AuctionOfferDTO> offers = dto.getOffers();
        for (AuctionOffer offer : auction.getOffers()){
            offers.add(AuctionOfferDTO.builder()
            .amount(offer.getAmount())
            .username(offer.getUser().getUsername())
            .submissionDate(offer.getSubmissionDate())
            .build());
        }

        return dto;
    }

    @PostMapping("/auctions/")
    @ResponseStatus(HttpStatus.CREATED)
    public Auction addAuction(@RequestBody CreateAuctionDTO dto){
        // TODO: validate
        return auctionService.createAuction(dto);
    }

//    @PostMapping("/auctions/{id}/offers")
//    @ResponseStatus(HttpStatus.CREATED)
//    public AuctionOffer addOffer(@PathVariable(value = "id") long auctionId, @RequestBody AuctionOffer offer){
//        val auction = this.getAuction(auctionId);
//        // TODO: validate offer
//        return auctionService.addOfferToAuction(auction, offer);
//    }
}
