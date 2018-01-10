package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.*;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.AuctionOffer;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.AuctionNotFoundException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.CategoryNotFoundException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.IllegalBidException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.AuctionService;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.CategoryService;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.UserService;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.impl.AuctionServiceImpl;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuctionController {
    private final AuctionService auctionService;
    private final UserService userService;
    private final CategoryService categoryService;


    @GetMapping("/auctions")
    public List<AuctionMiniDTO> getAuctions(@RequestParam Optional<String> username,
                                            @RequestParam Optional<Long> category){
        List<Auction> auctions;
        if (username.isPresent()){
            User user = userService.getUser(username.get());
            if (user == null){
                throw new IllegalArgumentException("user " + username.get() + " doesn't exist");
            }
            auctions = auctionService.getForUser(user);
        } else if (category.isPresent()){
            List<Category> categories = (List<Category>)categoryService.getBottomLevelCategories(category.get());
            auctions = auctionService.getFromCategories(categories);
        } else {
            auctions = auctionService.getAll();
        }

        List<AuctionMiniDTO> result = new ArrayList<>(auctions.size());
        auctions.forEach(a -> result.add(createAuctionMiniDTO(a)));
        return result;
    }

    @GetMapping("/auctions/{id}")
    public AuctionDTO getAuction(@PathVariable long id){
        val auction = auctionService.getAuction(id);
        if (auction == null){
            throw new AuctionNotFoundException(String.valueOf(id));
        }
        return createAuctionDTO(auction);
    }

    private AuctionMiniDTO createAuctionMiniDTO(Auction auction){
        return AuctionMiniDTO.builder()
                .currentPrice(auction.getCurrentPrice())
                .title(auction.getTitle())
                .id(auction.getId())
                .thumbnailUrl(auction.getThumbnailUrl())
                .build();
    }

    private AuctionDTO createAuctionDTO(Auction auction){
        val dto = createBasicDTO(auction);
        addCategoriesToDTO(dto, auction);
        addOffersToDTO(dto, auction);
        return dto;
    }

    private AuctionDTO createBasicDTO(Auction auction){
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
                .duration(auction.getDuration())
                .build();

        return dto;
    }
    private void addOffersToDTO(AuctionDTO dto, Auction auction) {
        List<AuctionOfferDTO> offers = dto.getOffers();
        for (AuctionOffer offer : auction.getOffers()){
            offers.add(createAuctionOfferDTO(offer));
        }
    }
    private void addCategoriesToDTO(AuctionDTO dto, Auction auction) {
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
    }
    private AuctionOfferDTO createAuctionOfferDTO(AuctionOffer offer){
        return AuctionOfferDTO.builder()
                .amount(offer.getAmount())
                .username(offer.getUser().getUsername())
                .submissionDate(offer.getSubmissionDate())
                .build();
    }

    @PostMapping("/auctions")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('STANDARD_USER')")
    public AuctionDTO addAuction(@RequestBody CreateAuctionDTO dto, Principal principalUser){
        User user = userService.getUser(principalUser.getName());
        Category category = categoryService.getCategory(dto.getCategory());
        return createAuctionDTO(auctionService.createAuction(dto, user, category));
    }

    @PutMapping("/auctions/{id}")
    @PreAuthorize("hasRole('STANDARD_USER')")
    public void updateAuction(@RequestBody CreateAuctionDTO dto, @PathVariable long id, Principal principalUser){
        Auction auction = auctionService.getAuction(id);
        User user = userService.getUser(principalUser.getName());
        validateOwner(auction, user);
        Category category = categoryService.getCategory(dto.getCategory());
        if (category == null){
            throw new CategoryNotFoundException();
        }
        auction.setCategory(category);
        auction.setDescription(dto.getDescription());
        auction.setStartingPrice(dto.getStartingPrice());
        LocalDateTime endDate = auction.getStartDateTime().plusDays(dto.getDuration());
        auction.setEndDateTime(endDate);
        auction.setDuration(dto.getDuration());
        auction.setTitle(dto.getTitle());
        auctionService.updateAuction(auction);
    }

    @DeleteMapping("/auctions/{id}")
    @PreAuthorize("hasRole('STANDARD_USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuction(@PathVariable long id, Principal principalUser){
        Auction auction = auctionService.getAuction(id);
        User user = userService.getUser(principalUser.getName());
        validateOwner(auction, user);
        auctionService.delete(auction);
    }

    @PostMapping("/auctions/{id}/bid")
    @PreAuthorize("hasRole('STANDARD_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public AuctionOfferDTO createBid(@RequestBody CreateOfferDTO dto, @PathVariable long id,
                          Principal principalUser){
        Auction auction = auctionService.getAuction(id);
        User user = userService.getUser(principalUser.getName());
        boolean requestValid = (auction.getOwner() != user)
                && dto.getAmount() > auction.getMinimalBid();
        if (!requestValid){
            throw new IllegalBidException();
        }
        AuctionOffer offer = new AuctionOffer();
        offer.setAmount(dto.getAmount());
        offer.setSubmissionDate(LocalDateTime.now());
        offer.setUser(user);
        auctionService.addOfferToAuction(auction, offer);
        return createAuctionOfferDTO(offer);
    }

    private void validateOwner(Auction auction, User user){
        if (auction.getOwner() != user){
            throw new AccessDeniedException("403 - only auction owner can modify it.");
        }
    }
}
