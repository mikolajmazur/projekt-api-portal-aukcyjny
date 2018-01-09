package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;

@Getter
public class UserRegisteredEvent extends ApplicationEvent {
    private User user;

    public UserRegisteredEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
