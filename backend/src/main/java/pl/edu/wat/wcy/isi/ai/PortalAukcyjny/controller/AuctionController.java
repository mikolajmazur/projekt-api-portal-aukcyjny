package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.AuctionDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.AuctionService;

import java.util.Collection;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuctionController {
    private final AuctionService auctionService;

    @GetMapping("/auctions/page/{pageNumber}")
    public Collection<AuctionDTO> getAuctions(@PathVariable(value = "pageNumber") int pageNumber){
        return auctionService.getAuctionPage(pageNumber);
    }

    @GetMapping("/auctions/{id}")
    public AuctionDTO getAuction(@PathVariable(value = "id") long id){
        // TODO: handle errors
        return auctionService.getAuction(id);
    }

    @DeleteMapping("/auctions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuction(@PathVariable(value = "id") long id){
        auctionService.deleteAuction(id);
        // TODO: handle org.springframework.dao.EmptyResultDataAccessException
        // when no entity matches id
    }

    @PutMapping("/auctions/{id}")
    public AuctionDTO updateAuctions(@PathVariable(value = "id") long id, @RequestBody AuctionDTO auction){
        // TODO: implement update function
        return null;
    }

    @PostMapping("/auctions")
    @ResponseStatus(HttpStatus.CREATED)
    public AuctionDTO createAuctions(@RequestBody Auction auction){
        return auctionService.addAuction(auction);
        // TODO: handle errors
    }
}
