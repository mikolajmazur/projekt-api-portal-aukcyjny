package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDateTime endDateTime;
    private LocalDateTime startDateTime;
    private int duration;
    private double startingPrice;
    private boolean isActive;
    @Lob
    private String description;
    private String photoUrl;
    private String thumbnailUrl;
    @JsonIgnore
    @OneToMany(
            mappedBy = "auction",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AuctionOffer> offers = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @Builder
    public Auction(String title, LocalDateTime endDateTime, LocalDateTime startDateTime, double startingPrice,
                   boolean isActive, String description, String photoUrl, String thumbnailUrl, Category category,
                   User owner, int duration) {
        this.title = title;
        this.endDateTime = endDateTime;
        this.startDateTime = startDateTime;
        this.startingPrice = startingPrice;
        this.isActive = isActive;
        this.description = description;
        this.photoUrl = photoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.category = category;
        this.owner = owner;
        this.duration = duration;
    }


    public void addOffer(AuctionOffer offer){
        offers.add(offer);
        offer.setAuction(this);
    }

    public void removeOffer(AuctionOffer offfer){
        offers.remove(offfer);
        offfer.setAuction(null);
    }

    public double getCurrentPrice(){
        double currentPrice;
        if(offers.isEmpty()){
            currentPrice = this.startingPrice;
        } else {
            currentPrice = this.offers.get(offers.size() - 1).getAmount();
        }
        return currentPrice;
    }

    public boolean hasOffers(){
        return !this.offers.isEmpty();
    }

    public AuctionOffer getTopBid(){
        return this.offers.get(this.offers.size() - 1);
    }

    public double getMinimalBid(){
        return this.getCurrentPrice() * 1.05;
    }
}
