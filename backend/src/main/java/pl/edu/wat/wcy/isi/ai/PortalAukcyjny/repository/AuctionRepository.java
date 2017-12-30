package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;

import java.util.Collection;
import java.util.List;

@Repository
public interface AuctionRepository extends PagingAndSortingRepository<Auction, Long> {
//    Collection<Auction> findAllByCategory(Category category, Pageable pageable);
//    Collection<Auction> findAllByCategory(Category category);
    Collection<Auction> findAllByCategoryIdIn(Collection<Long> ids);
    //Collection<Auction> findAllByCategoryIn(Collection<Category> categories);
    Page<Auction> findAllByCategoryIn(Collection<Category> categories, Pageable pageable);
}
