package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.UserDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.event.UserRegisteredEvent;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception.RecaptchaInvalidException;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.RecaptchaVerifierService;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.UserService;

@RestController
@RequestMapping("api/v2")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final RecaptchaVerifierService recaptchaVerifier;

    @PostMapping("/register")
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
}
