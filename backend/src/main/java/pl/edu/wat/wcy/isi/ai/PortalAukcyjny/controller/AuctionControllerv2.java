package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.AuctionMiniDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CreateAuctionDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.AuctionOffer;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.AuctionNotFoundException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.AuctionServiceV2;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuctionControllerv2 {
    private final AuctionServiceV2 auctionService;

    @GetMapping("/auctions")
    public List<AuctionMiniDTO> getAuctionsList(){
        return auctionService.getAll();
    }


    @GetMapping("/auctions/{id}")
    public Auction getAuction(@PathVariable(value = "id") long id){
        val auction = auctionService.getAuction(id);
        if (auction == null){
            throw new AuctionNotFoundException(String.valueOf(id));
        }
        return auction;
    }

    @PostMapping("/auctions/")
    @ResponseStatus(HttpStatus.CREATED)
    public Auction addAuction(@RequestBody CreateAuctionDTO dto){
        // TODO: validate
        return auctionService.createAuction(dto);
    }

    @PostMapping("/auctions/{id}/offers")
    @ResponseStatus(HttpStatus.CREATED)
    public AuctionOffer addOffer(@PathVariable(value = "id") long auctionId, @RequestBody AuctionOffer offer){
        val auction = this.getAuction(auctionId);
        // TODO: validate offer
        return auctionService.addOfferToAuction(auction, offer);
    }
}
