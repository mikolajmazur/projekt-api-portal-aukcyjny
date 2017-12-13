package pl.edu.wat.wcy.isi.ai.PortalAukcyjny;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CreateAuctionDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CreateCategoryDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.AuctionOffer;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.AuctionRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.AuctionService;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.AuctionServiceV2;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.CategoryService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataLoader implements ApplicationRunner {
    private final AuctionRepository auctionRepository;
    private final AuctionServiceV2 auctionService;
    private final CategoryService categoryService;

    public void run(ApplicationArguments args) {
//        val auction1 = Auction.builder()
//                .title("Skarpety")
//                .description("Aukcja 1")
//                .photoUrl("auctionImg/1.jpg")
//                .startingPrice(5)
//                .startDateTime(LocalDateTime.parse("2017-12-05T15:15:15"))
//                .endDateTime(LocalDateTime.parse("2018-01-01T12:13:14"))
//                .build();
//        val auction1Offer1 = AuctionOffer.builder()
//                .amount(10.5)
//                .submissionDate(LocalDateTime.parse("2017-12-06T10:10:10"))
//                .build();
//        val auction1Offer2 = AuctionOffer.builder()
//                .amount(12.5)
//                .submissionDate(LocalDateTime.parse("2017-12-06T10:15:10"))
//                .build();
//        auction1.addOffer(auction1Offer1);
//        auction1.addOffer(auction1Offer2);
//        auctionRepository.save(auction1);
//
//        val auction2 = CreateAuctionDTO.builder()
//                .description("Super przedmiot!!! <br> Kupujta")
//                .duration(7)
//                .startingPrice(10.5)
//                .title("Przedmiot 1")
//                .build();
//        auctionService.createAuction(auction2);
//        val auction3 = CreateAuctionDTO.builder()
//                .description("Super przedmiot!!! <br> Kupujta")
//                .duration(10)
//                .startingPrice(300.5)
//                .title("Przedmiot 2")
//                .build();
//        auctionService.createAuction(auction3);
//        val auction4 = CreateAuctionDTO.builder()
//                .description("Super przedmiot!!! <br> Kupujta")
//                .duration(7)
//                .startingPrice(10.5)
//                .title("Przedmiot 3")
//                .build();
//        auctionService.createAuction(auction4);
//        val auction5 = CreateAuctionDTO.builder()
//                .description("Super przedmiot!!! <br> Kupujta")
//                .duration(7)
//                .startingPrice(10.5)
//                .title("Przedmiot 4")
//                .build();
//        auctionService.createAuction(auction5);

//        val category1 = new CreateCategoryDTO("Kategoria1", null);
//        val category2 = new CreateCategoryDTO("Kategoria2", 1L);
//        val category3 = new CreateCategoryDTO("Kategoria3", 2L);
//        categoryService.createCategory(category1);
//        categoryService.createCategory(category2);
//        categoryService.createCategory(category3);

//        val auction2 = Auction.builder()
//                .title("Kompjuter")
//                .description("Aukcja 2")
//                .photoUrl("auctionImg/2.jpg")
//                .currentPrice(2000.5)
//                .endDateTime(LocalDateTime.parse("2018-02-03T12:13:14"))
//                .build();
//        val auction3 = Auction.builder()
//                .title("Kubek")
//                .description("Aukcja 3")
//                .photoUrl("auctionImg/3.jpg")
//                .currentPrice(2.5)
//                .endDateTime(LocalDateTime.parse("2017-12-20T12:13:14"))
//                .build();
//
//        auctionRepository.save(auction1);
//        auctionRepository.save(auction2);
//        auctionRepository.save(auction3);
    }
}
