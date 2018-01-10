package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import ch.qos.logback.core.joran.action.ActionUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.AuctionMiniDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CreateAuctionDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.AuctionOffer;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AuctionService {
    Auction getAuction(long id);
    AuctionOffer addOfferToAuction(Auction auction, AuctionOffer offer);
    Auction createAuction(CreateAuctionDTO dto, User user, Category category);
    List<Auction> getAll();
    Page<Auction> getAuctionsFromCategoryAndSubCategories(Category category, Pageable pageable);
    void updateAuction(Auction auction);
    List<Auction> getFromCategory(Category category);
    List<Auction> getFromCategories(Collection<Category> categories);
    List<Auction> getForUser(User user);
    void delete(Auction auction);
    AuctionOffer addOffer(AuctionOffer offer, Auction auction);
}
