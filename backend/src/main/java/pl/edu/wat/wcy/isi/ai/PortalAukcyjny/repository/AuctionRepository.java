package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;

import java.util.Collection;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Collection<Auction> findAllByCategoryIdIn(Collection<Long> ids);
    Page<Auction> findAllByCategoryIn(Collection<Category> categories, Pageable pageable);
    List<Auction> findAllByOwner(User owner);
    List<Auction> findAllByCategoryIn(Collection<Category> categories);
}
