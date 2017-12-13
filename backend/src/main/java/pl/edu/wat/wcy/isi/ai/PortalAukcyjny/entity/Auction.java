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
    private double startingPrice;
    @Lob
    private String description;
    private String photoUrl;
    @OneToMany(
            mappedBy = "auction",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AuctionOffer> offers = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonIgnore
    private Category category;

    @Builder
    public Auction(String title, LocalDateTime endDateTime, LocalDateTime startDateTime, String description, String photoUrl, double startingPrice) {
        this.title = title;
        this.endDateTime = endDateTime;
        this.startDateTime = startDateTime;
        this.description = description;
        this.photoUrl = photoUrl;
        this.startingPrice = startingPrice;
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
}
