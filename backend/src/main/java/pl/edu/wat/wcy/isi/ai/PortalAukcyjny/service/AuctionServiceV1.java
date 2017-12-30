package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.AuctionRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.AuctionDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuctionServiceV1 {
    private final AuctionRepository auctionRepository;
    private final int PAGE_SIZE = 20;

    public AuctionDTO getAuction(long id){
        val auction = auctionRepository.findOne(id);
        return auctionToDTO(auction);
    }

    public Collection<AuctionDTO> getAuctionPage(int pageNumber){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);
        Page<Auction> page = auctionRepository.findAll(pageRequest);
        val list = new ArrayList<AuctionDTO>();
        page.forEach(auction -> list.add(auctionToDTO(auction)));

        return list;
    }

    public void deleteAuction(long auctionId){
        auctionRepository.delete(auctionId);
    }

    public AuctionDTO addAuction(Auction auction){
        auctionRepository.save(auction);
        return auctionToDTO(auction);
    }

    public AuctionDTO updateAuction(AuctionDTO auctionDTO){
        Auction auction = dTOToAuction(auctionDTO);
        return auctionToDTO(auctionRepository.save(auction));
    }

    private AuctionDTO auctionToDTO(Auction auction){
//        return AuctionDTO.builder()
//                .description(auction.getDescription())
//                .currentPrice(auction.getCurrentPrice())
//                .endDateTime(auction.getEndDateTime())
//                .id(auction.getId())
//                .photoUrl(auction.getPhotoUrl())
//                .title(auction.getTitle())
//                .build();
        throw new NotImplementedException();
    }

    private Auction dTOToAuction(AuctionDTO auctionDTO){
//        return Auction.builder()
//                .title(auctionDTO.getTitle())
//                .description(auctionDTO.getDescription())
//                .currentPrice(auctionDTO.getCurrentPrice())
//                .endDateTime(auctionDTO.getEndDateTime())
//                .id(auctionDTO.getId())
//                .photoUrl(auctionDTO.getPhotoUrl())
//                .build();

        throw new NotImplementedException();
    }
}
