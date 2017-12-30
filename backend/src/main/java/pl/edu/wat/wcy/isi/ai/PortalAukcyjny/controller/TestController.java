package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.AuctionMiniDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.event.AuctionEndedEvent;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.AuctionRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.CategoryService;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.EmailService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/test")
public class TestController {
    @Autowired
    private AuctionRepository repo;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/category/{id}")
    public List<AuctionMiniDTO> getAuctionsFromCategory(@PathVariable long id){
        List<Long> list = new ArrayList<>();
        //list.add(id);
        list.add(10L);
        list.add(11L);
        List<AuctionMiniDTO> result = new ArrayList<>();
        repo.findAllByCategoryIdIn(list).forEach(a -> result.add(createAuctionMiniDTO(a)));

//        List<Category> list = new ArrayList<>();
//        list.add(categoryService.getCategory(id));
//        repo.findAllByCategoryIn(list).forEach(a -> result.add(createAuctionMiniDTO(a)));
        return result;
    }

    private AuctionMiniDTO createAuctionMiniDTO(Auction auction){
        // TODO: remove hidden dependency in thumbnailUrl generation
        String[] splitPhotoUrl = auction.getPhotoUrl().split("\\.");
        String thumbnailUrl = splitPhotoUrl[0] + "_thumbnail." + splitPhotoUrl[1];
        return AuctionMiniDTO.builder()
                .currentPrice(auction.getCurrentPrice())
                .title(auction.getTitle())
                .id(auction.getId())
                .thumbnailUrl(thumbnailUrl)
                .build();

    }

    @GetMapping("/mail")
    public void sendMail(){
//        String to = "nithiael@gmail.com";
//        String subject = "Test e-mail";
//        String text = "Wolololo";
//        emailService.sendSimpleMessage(to, subject, text);
        this.publisher.publishEvent(new AuctionEndedEvent(this, 1));
    }
}
