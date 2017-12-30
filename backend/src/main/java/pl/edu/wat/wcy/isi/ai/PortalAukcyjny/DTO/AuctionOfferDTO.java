package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
@Data
public class AuctionOfferDTO {
    private String username;
    private Long userId;
    private double amount;
    private LocalDateTime submissionDate;
    private Long auctionId;
}
