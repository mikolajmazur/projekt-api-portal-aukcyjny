package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
@Builder
public class AuctionDTO {
    private long id;
    private String title;
    private double currentPrice;
    private LocalDateTime endDateTime;
    private String description;
    private String photoUrl;
}
