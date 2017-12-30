package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CategoryDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.AuctionService;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.CategoryService;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {
    @Mock
    CategoryService service;
    @Mock
    AuctionService auctionService;

    CategoryController controller;

    @Before
    public void setUp() throws Exception {
        controller = new CategoryController(service, auctionService);
    }

    @Test
    public void shouldGetAllCategories() {
        Category topLevelCategory = new Category();
        Category level1Category1 = new Category();;
        Category level1Category2 = new Category();
        Category level2Category1 = new Category();
        Category level2Category2 = new Category();
        level1Category1.addChildCategory(level2Category1);
        level1Category1.addChildCategory(level2Category2);
        topLevelCategory.addChildCategory(level1Category1);
        topLevelCategory.addChildCategory(level1Category2);

        Iterable<CategoryDTO> result = controller.getAllCategories();

        //assertThat(result, hasItems());
    }
}
