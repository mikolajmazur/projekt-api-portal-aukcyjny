package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.AuctionRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.impl.AuctionServiceImpl;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AuctionServiceImplTest {
    private AuctionService service;

    @Mock
    private AuctionRepository auctionRepository;

    @Before
    public void setUp() throws Exception {
        service = new AuctionServiceImpl(auctionRepository);
    }

    @Test
    public void shouldGetCorrectAuctionGivenId() {
        Long id = 1L;
        Auction auction = new Auction();
        auction.setId(id);
        Mockito.when(auctionRepository.findOne(id)).thenReturn(auction);

        Auction result = service.getAuction(id);

        Assert.assertEquals(id, result.getId());
    }

    @Test
    public void shouldGetAuctionsFromSubCategories() {
        Auction auction1 = new Auction();
        auction1.setId(1L);
        Auction auction2 = new Auction();
        auction2.setId(2L);
        Auction auction3 = new Auction();
        auction3.setId(3L);
        Category topLevelCategory = new Category();
        Category subCategory1 = new Category();;
        Category subCategory2 = new Category();
        topLevelCategory.addChildCategory(subCategory1);
        topLevelCategory.addChildCategory(subCategory2);
        subCategory1.addAuction(auction1);
        subCategory1.addAuction(auction2);
        subCategory2.addAuction(auction3);

        Pageable pageable = new PageRequest(0, 20);

        List<Auction> result = service.getAuctionsFromCategoryAndSubCategories(topLevelCategory, pageable).getContent();

        assertThat(result, hasSize(3));
        assertThat(result, hasItems(auction1, auction2, auction3));
    }
}
