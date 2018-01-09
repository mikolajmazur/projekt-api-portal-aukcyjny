package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.event.UserRegisteredEvent;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.EmailService;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRegisteredListener {
    private final EmailService emailService;
    private static final String subjectTemplate = "Witamy w Cebullegro";
    private static final String messageTemplate = "Witaj, %s! %n" +
            "Gratulujemy rejestracji w Cebullegro. Świat januszowych transakcji stoi przed tobą otworem!%n" +
            "Twoja nazwa użytkownika w portalu: %s %n." +
            "Pozdrawiamy, zespół Cebullegro";

    @EventListener
    public void handleEvent(UserRegisteredEvent event){
        User user = event.getUser();
        String message = String.format(messageTemplate, user.getFirstName() + " " + user.getLastName(), user.getUsername());
        this.emailService.sendMessageTo(user, subjectTemplate, message);
    }
}
