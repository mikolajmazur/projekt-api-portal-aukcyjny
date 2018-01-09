package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service;

import org.springframework.stereotype.Service;

@Service
public interface RecaptchaVerifierService {
    boolean isCaptchaValid(String recaptchaResponse);
}
