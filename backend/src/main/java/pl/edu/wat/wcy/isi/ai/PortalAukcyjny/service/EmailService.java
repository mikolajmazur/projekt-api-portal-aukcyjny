package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}
