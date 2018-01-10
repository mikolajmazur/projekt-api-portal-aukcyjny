package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.UserDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.event.UserRegisteredEvent;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.RecaptchaInvalidException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.RecaptchaVerifierService;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v2")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final RecaptchaVerifierService recaptchaVerifier;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDTO userDto){
        boolean recaptchaValid = recaptchaVerifier.isCaptchaValid(userDto.getRecaptcha());
        if (!recaptchaValid){
            throw new RecaptchaInvalidException();
        }
        User user = userService.createNormalUser(userDto);
        UserRegisteredEvent event = new UserRegisteredEvent(this, user);
        publisher.publishEvent(event);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getUsers(){
        List<User> users = userService.getAll();
        List<UserDTO> result = new ArrayList<>(users.size());
        users.forEach(u -> result.add(createUserDTO(u)));

        return result;
    }

    private UserDTO createUserDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        user.getRoles().forEach(role -> dto.getRoles().add(role.getRoleName()));

        return dto;
    }
}
