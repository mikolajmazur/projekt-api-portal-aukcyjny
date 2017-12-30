package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.AuctionMiniDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CreateAuctionDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.AuctionOffer;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;

import java.util.Collection;
import java.util.List;

public interface AuctionService {
    Auction getAuction(long id);
    AuctionOffer addOfferToAuction(Auction auction, AuctionOffer offer);
    Auction createAuction(CreateAuctionDTO dto);
    List<AuctionMiniDTO> getAll();
    Page<Auction> getAuctionsFromCategoryAndSubCategories(Category category, Pageable pageable);
    void updateAuction(Auction auction);
}
