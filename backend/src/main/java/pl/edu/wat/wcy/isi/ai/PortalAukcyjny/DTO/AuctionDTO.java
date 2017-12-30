package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.AuctionOffer;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AuctionDTO {
    private long id;
    private String title;
    private double currentPrice;
    private double minimalBid;
    private LocalDateTime endDateTime;
    private LocalDateTime startDateTime;
    private String description;
    private String photoUrl;
    private long sellerId;
    private String sellerUsername;
    @Builder.Default
    private List<CategoryDTO> containingCategories = new ArrayList<>();
    @Builder.Default
    private List<AuctionOfferDTO> offers = new ArrayList<>();
}
