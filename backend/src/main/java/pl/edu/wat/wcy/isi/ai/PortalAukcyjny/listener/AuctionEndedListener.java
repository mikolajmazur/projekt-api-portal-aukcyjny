package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.event.AuctionEndedEvent;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.AuctionService;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.EmailService;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuctionEndedListener {
    private final EmailService emailService;
    private final AuctionService auctionService;
    private Auction auction;
    private final String emailTemplate = "Auction %d ended";
    private final String auctionEndedSubjectTemplate = "Koniec aukcji nr %d - %s";
    private final String notSoldMessageTemplate = "Nie udało się sprzedać ...";
    private final String soldMessageTemplate = "Sprzedałeś %s ... bla bla bla ... za %.2f ... %n Gratulujemy!";
    private final String messageToWinnerSubjectTemplate = "Wygrałeś aukcję nr %d - %s";
    private final String messageToWinnerTemplate = "Bla bla bla ... wygrałeś ... bla bla bla kwota %.2f";

    @EventListener
    public void handleAuctionEnded(AuctionEndedEvent event){
        this.auction = auctionService.getAuction(event.getAuctionId());
        if (this.auction.hasOffers()){
            handleSoldAuction();
        } else {
            handleAuctionNotSold();
        }

        auction.setActive(false);
        auctionService.updateAuction(auction);
    }

    private void handleSoldAuction() {
        String sellersEmail = auction.getOwner().getEmail();
        String sellersMessageSubject = String.format(auctionEndedSubjectTemplate, auction.getId(), auction.getTitle());
        String sellersMessage = String.format(soldMessageTemplate, auction.getTitle(), auction.getCurrentPrice());
        emailService.sendSimpleMessage(sellersEmail, sellersMessageSubject, sellersMessage);

        User winner = auction.getTopBid().getUser();
        String winnerEmail = winner.getEmail();
        String winnerMessageSubject = String.format(messageToWinnerSubjectTemplate, auction.getId(), auction.getTitle());
        String winnerMessage = String.format(messageToWinnerTemplate, auction.getCurrentPrice());
        emailService.sendSimpleMessage(winnerEmail, winnerMessageSubject, winnerMessage);
    }

    private void handleAuctionNotSold() {
        String to = auction.getOwner().getEmail();
        String subject = String.format(auctionEndedSubjectTemplate, auction.getId(), auction.getTitle());
        String text = notSoldMessageTemplate;
        emailService.sendSimpleMessage(to, subject, text);
    }
}
