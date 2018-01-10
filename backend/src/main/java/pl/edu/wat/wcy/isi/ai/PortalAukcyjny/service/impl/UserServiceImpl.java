package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.UserDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Role;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.EmailTakenException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.UsernameTakenException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.RoleRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.UserRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User createNormalUser(UserDTO userDto) {
        if (isUsernameTaken(userDto.getUsername())){
            throw new UsernameTakenException();
        }
        if (isEmailUsed(userDto.getEmail())){
            throw new EmailTakenException();
        }
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = User.builder()
                .password(hashedPassword)
                .username(userDto.getUsername())
                .lastName(userDto.getLastName())
                .firstName(userDto.getFirstName())
                .email(userDto.getEmail())
                .build();

        Role userRole = roleRepository.getRoleByRoleName("ROLE_STANDARD_USER");
        user.addRole(userRole);
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public boolean isEmailUsed(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
