package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Role;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.EmailTakenException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.UsernameTakenException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.RoleRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.UserRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.UserService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void createNormalUser(User user) {
        if (isUsernameTaken(user.getUsername())){
            throw new UsernameTakenException();
        }
        if (isEmailUsed(user.getEmail())){
            throw new EmailTakenException();
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        Role userRole = roleRepository.getRoleByRoleName("ROLE_STANDARD_USER");
        user.addRole(userRole);
        userRepository.save(user);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public boolean isEmailUsed(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
