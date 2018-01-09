package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;

@Service
public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessageTo(User user, String subject, String text);
}
