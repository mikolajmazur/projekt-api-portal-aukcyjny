package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;

public interface UserService {
    void createNormalUser(User user);
    boolean isUsernameTaken(String username);
    boolean isEmailUsed(String email);
}
