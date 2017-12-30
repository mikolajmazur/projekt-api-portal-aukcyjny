package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.CategoryRepository;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {
    @Mock
    private CategoryRepository repo;

    private CategoryService service;

    @Before
    public void setUp() throws Exception {
        service = new CategoryServiceImpl(repo);
    }

    @Test
    public void shouldGetCorrectBottomCategoriesGivenTree() {
//                1
//             /    \
//            2     3
//          /   \
//         4    5
//        should get 3,4,5

        Category category1 = new Category();
        Category category2 = new Category();
        Category category3 = new Category();
        category1.addChildCategory(category2);
        category1.addChildCategory(category3);
        Category category4 = new Category();
        Category category5 = new Category();
        category2.addChildCategory(category4);
        category2.addChildCategory(category5);

        Collection<Category> result = service.getBottomLevelCategories(category1);

        assertThat(result, hasSize(3));
        assertThat(result, hasItems(category3, category4, category5));
    }

    @Test
    public void shouldReturnGivenCategoryWhenItsBottomCategory() {
        Category category1 = new Category();

        Collection<Category> result = service.getBottomLevelCategories(category1);

        assertThat(result, hasSize(1));
        assertThat(result, hasItems(category1));
    }
}
