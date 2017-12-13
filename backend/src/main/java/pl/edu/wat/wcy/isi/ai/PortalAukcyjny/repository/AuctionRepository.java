package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;

@Repository
public interface AuctionRepository extends PagingAndSortingRepository<Auction, Long> {
}
