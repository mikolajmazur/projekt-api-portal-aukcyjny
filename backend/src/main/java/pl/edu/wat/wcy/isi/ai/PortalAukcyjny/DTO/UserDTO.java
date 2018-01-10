package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;
    private String firstName;
    private String lastName;
    private String recaptcha;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> roles = new ArrayList<>();
}
