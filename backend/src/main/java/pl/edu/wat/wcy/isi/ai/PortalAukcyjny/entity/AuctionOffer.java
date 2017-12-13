package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double amount;
    private LocalDateTime submissionDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Auction auction;
}
