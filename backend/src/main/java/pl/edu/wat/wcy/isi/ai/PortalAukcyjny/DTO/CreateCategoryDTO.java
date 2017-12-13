package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCategoryDTO {
    private String name;
    private Long parentId;
}
