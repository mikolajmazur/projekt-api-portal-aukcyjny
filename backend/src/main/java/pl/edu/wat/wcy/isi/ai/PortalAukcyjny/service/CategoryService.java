package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CategoryDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDTO dto);
    Collection<Category> getAllCategories();
    Category updateCategory(Long id, CategoryDTO category);
    Category getCategory(long categoryId);
    Collection<Category> getBottomLevelCategories(long categoryId);
    Collection<Category> getBottomLevelCategories(Category category);
}
