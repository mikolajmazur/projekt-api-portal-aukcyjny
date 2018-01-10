package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.AuctionMiniDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CreateAuctionDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.AuctionOffer;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.InvalidPageDataException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.AuctionRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.AuctionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository auctionRepository;
    private final int PAGE_SIZE = 20;
    @Value("${default-photo-url}")
    private String defaultPhotoUrl;
    @Value("${default-thumbnail-url}")
    private String defaultThumbnailUrl;

    public Auction getAuction(long id) {
        return auctionRepository.findOne(id);
    }

    public AuctionOffer addOfferToAuction(Auction auction, AuctionOffer offer) {
        offer.setSubmissionDate(LocalDateTime.now());

        auction.addOffer(offer);
        auctionRepository.save(auction);

        return offer;
    }

    public Auction createAuction(CreateAuctionDTO dto, User creator, Category category){
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(dto.getDuration());
        Auction auction = Auction.builder()
                .description(dto.getDescription())
                .startDateTime(startDate)
                .endDateTime(endDate)
                .photoUrl(defaultPhotoUrl)
                .thumbnailUrl(defaultThumbnailUrl)
                .isActive(true)
                .startingPrice(dto.getStartingPrice())
                .title(dto.getTitle())
                .owner(creator)
                .category(category)
                .duration(dto.getDuration())
                .build();

        auctionRepository.save(auction);
        return auction;
    }

    public List<Auction> getAll(){
        return auctionRepository.findAll();
    }

    @Override
    public List<Auction> getFromCategory(Category category) {
        return null;
    }

    @Override
    public List<Auction> getForUser(User user) {
        return auctionRepository.findAllByOwner(user);
    }

    @Override
    public List<Auction> getFromCategories(Collection<Category> categories) {
        return auctionRepository.findAllByCategoryIn(categories);
    }

    @Override
    public Page<Auction> getAuctionsFromCategoryAndSubCategories(Category category, Pageable pageable) {
        Collection<Category> bottomLevelCategories = getBottomLevelCategories(category);
        return auctionRepository.findAllByCategoryIn(bottomLevelCategories, pageable);
    }

    private Collection<Category> getBottomLevelCategories(Category category) {
        List<Category> list = new ArrayList<>();
        if (category.getChildren().isEmpty()){
            list.add(category);
        } else {
            for (Category subCategory : category.getChildren()){
                list.addAll(getBottomLevelCategories(subCategory));
            }
        }
        return list;
    }

    @Override
    public void updateAuction(Auction auction) {
        this.auctionRepository.save(auction);
    }

    @Override
    public void delete(Auction auction) {
        this.auctionRepository.delete(auction);
    }

    @Override
    public AuctionOffer addOffer(AuctionOffer offer, Auction auction) {
        auction.addOffer(offer);
        auctionRepository.save(auction);
        return offer;
    }
}
