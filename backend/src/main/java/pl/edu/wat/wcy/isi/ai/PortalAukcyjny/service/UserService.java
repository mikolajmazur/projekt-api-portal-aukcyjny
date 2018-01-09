package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.UserDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;

public interface UserService {
    User createNormalUser(UserDTO user);
    boolean isUsernameTaken(String username);
    boolean isEmailUsed(String email);
}
