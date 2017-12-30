package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CategoryDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.CategoryNotFoundException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.InvalidCategoryException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repo;

    public Category createCategory(CategoryDTO dto){
        val category = new Category();
        category.setName(dto.getName());
        repo.save(category);
        Long parentId = dto.getParentId();
        if (parentId != null){
            val parent = repo.findOne(parentId);
            parent.addChildCategory(category);
            repo.save(parent);
        }

        return category;
    }

    @Override
    public Collection<Category> getBottomLevelCategories(long categoryId) {
        val category = this.getCategory(categoryId);
        if (category == null){
            throw new CategoryNotFoundException("getBottomLevelCategories id: " + categoryId);
        }
        return getBottomLevelCategories(category);
    }

    @Override
    public Collection<Category> getBottomLevelCategories(Category category) {
        List<Category> list = new ArrayList<>();
        if (category.getChildren().isEmpty()){
            list.add(category);
        } else {
            for (Category subCategory : category.getChildren()){
                list.addAll(getBottomLevelCategories(subCategory));
            }
        }
        return list;
    }

    @Override
    public Category getCategory(long categoryId) {
        return repo.findOne(categoryId);
    }

    public Collection<Category> getAllCategories() {
        return repo.findAll();
    }

    public Category updateCategory(Long id, CategoryDTO category) {
        boolean parentCategoryCorrect = isParentCategoryCorrect(category.getParentId());
        if (!parentCategoryCorrect){
            throw new InvalidCategoryException("updatego category " + id + " - parent category invalid " + category.getParentId());
        }

        val categoryToUpdate = repo.findOne(id);
        categoryToUpdate.setName(category.getName());
        if (!category.getParentId().equals(categoryToUpdate.getParent().getId())){
            val parent = repo.findOne(category.getParentId());
            categoryToUpdate.setParent(parent);
        }

        repo.save(categoryToUpdate);
        return categoryToUpdate;
    }

    private boolean isParentCategoryCorrect(Long categoryId){
        if (categoryId == null){
            // top level category
            return true;
        } else {
            return repo.exists(categoryId);
        }

    }
}
