package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AuctionEndedEvent extends ApplicationEvent {
    private long auctionId;

    public AuctionEndedEvent(Object source, long auctionId) {
        super(source);
        this.auctionId = auctionId;
    }
}
