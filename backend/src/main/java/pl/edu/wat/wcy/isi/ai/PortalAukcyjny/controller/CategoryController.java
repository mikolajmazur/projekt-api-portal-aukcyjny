package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.AuctionMiniDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.CategoryDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.CategoryNotFoundException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.InvalidPageDataException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.AuctionService;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.CategoryService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

@RestController
@RequestMapping("api/v2")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {
    private final CategoryService categoryService;
    private final AuctionService auctionService;
    private final int defaultPageSize = 20;

    @GetMapping("/categories")
    public Iterable<CategoryDTO> getAllCategories(){
        Collection<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> dtos = new ArrayList<>();

        for (Category category : categories){
            dtos.add(createCategoryDTOFrom(category));
        }

        return dtos;
    }

    private CategoryDTO createCategoryDTOFrom(Category category){
        CategoryDTO dto = createBasicCategoryDTO(category);
        addChildCategoriesToDTO(category, dto);
        addParentCategoriesToDTO(category, dto);
        return dto;
    }

    private CategoryDTO createBasicCategoryDTO(Category category){
        return CategoryDTO.builder()
                .name(category.getName())
                .id(category.getId())
                .build();
    }

    private void addChildCategoriesToDTO(Category category, CategoryDTO dto){
        if (!category.getChildren().isEmpty()){
            for (Category subCategory : category.getChildren()) {
                val subDTO = createBasicCategoryDTO(subCategory);
                addChildCategoriesToDTO(subCategory, subDTO);
                dto.getSubCategories().add(subDTO);
            }
        }
    }

    private void addParentCategoriesToDTO(Category category, CategoryDTO dto){
        Category parent = category.getParent();
        while (parent != null){
            dto.getParentCategories().add(createBasicCategoryDTO(parent));
            parent = parent.getParent();
        }
        if (!dto.getParentCategories().isEmpty()){
            Collections.reverse(dto.getParentCategories());
        }
    }

    @GetMapping("categories/{id}/auctions")
    public Page<AuctionMiniDTO> getAuctionsInCategory(
            @PathVariable(value = "id") long categoryId,
            @RequestParam(value = "page") Optional<Integer> pageNumber,
            @RequestParam(value = "pageSize") Optional<Integer> pageSize){
        Category category = categoryService.getCategory(categoryId);
        if (category == null){
            throw new CategoryNotFoundException("getAuctionsInCategory id: " + categoryId);
        }

        Pageable pageRequest = createPageRequest(pageNumber, pageSize);
        Page<Auction> auctionsPage = auctionService.getAuctionsFromCategoryAndSubCategories(category, pageRequest);

        return createPage(auctionsPage, pageRequest);
    }

    private Page<AuctionMiniDTO> createPage(Page<Auction> auctionsPage, Pageable pageDetails){
        List<AuctionMiniDTO> list = new ArrayList<>();
        auctionsPage.forEach(a -> list.add(createAuctionMiniDTO(a)));
        return new PageImpl<>(list, pageDetails, auctionsPage.getTotalElements());
    }

    private Pageable createPageRequest(Optional<Integer> pageNumber, Optional<Integer> pageSize){
        int actuallPageNumber = pageNumber.isPresent() ? pageNumber.get() - 1 : 0;
        int actuallPageSize = pageSize.orElse(defaultPageSize);
        if (actuallPageNumber < 0 || actuallPageSize < 1){
            throw new InvalidPageDataException();
        }
        return new PageRequest(actuallPageNumber, actuallPageSize);
    }

    private AuctionMiniDTO createAuctionMiniDTO(Auction auction){
        return AuctionMiniDTO.builder()
                .currentPrice(auction.getCurrentPrice())
                .title(auction.getTitle())
                .id(auction.getId())
                .thumbnailUrl(auction.getThumbnailUrl())
                .build();
    }

    @GetMapping("/categories/{id}")
    public CategoryDTO getCategory(@PathVariable long id){
        val category = categoryService.getCategory(id);
        return createCategoryDTOFrom(category);
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody CategoryDTO dto){
        return categoryService.createCategory(dto);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable(value = "id") long id,@RequestBody CategoryDTO category){
        return categoryService.updateCategory(id, category);
    }
}
