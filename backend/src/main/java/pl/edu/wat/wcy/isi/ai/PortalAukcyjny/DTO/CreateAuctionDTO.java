package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAuctionDTO {
    private String title;
    private double startingPrice;
    private String description;
    private int duration;
    private Long category;
}
