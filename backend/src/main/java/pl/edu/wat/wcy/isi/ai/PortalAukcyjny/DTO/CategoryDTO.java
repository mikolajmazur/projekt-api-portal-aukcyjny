package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long parentId;
    @Builder.Default
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryDTO> subCategories = new ArrayList<>();
    @Builder.Default
    private List<CategoryDTO> parentCategories = new ArrayList<>();
}
