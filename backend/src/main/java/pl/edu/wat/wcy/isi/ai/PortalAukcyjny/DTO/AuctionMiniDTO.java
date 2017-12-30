package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuctionMiniDTO {
    private long id;
    private String title;
    private double currentPrice;
    private String thumbnailUrl;
}
