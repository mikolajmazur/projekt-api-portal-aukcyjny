package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CreateCategoryDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.CategoryRepository;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {
    private final CategoryRepository repo;

    public Category createCategory(CreateCategoryDTO dto){
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

    public Iterable<Category> getAllCategories() {
//        List<Category> categoryList = new ArrayList<>();
//        for (Category category : repo.findAll()){
//            if (category.getParent() ==  null){
//                categoryList.add(category);
//            }
//        }
//        return categoryList;

        return repo.findAllCategories();
    }
}
