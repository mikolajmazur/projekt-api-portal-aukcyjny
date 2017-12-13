package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO;

import java.time.LocalDateTime;

public class AuctionDTOv2 {
    private long id;
    private String title;
    private double currentPrice;
    private LocalDateTime endDateTime;
    private String description;
    private String photoUrl;
}
