package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.RecaptchaVerifierService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RecaptchaVerifierServiceImpl implements RecaptchaVerifierService {
    @Value("${google.recaptcha.client-secret}")
    private String clientSecret;
    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private final RestTemplateBuilder restTemplateBuilder;;

    @Override
    @SuppressWarnings("unchecked")
    public boolean isCaptchaValid(String recaptchaResponse) {
        Map<String, String> body = new HashMap<>();
        body.put("secret", clientSecret);
        body.put("response", recaptchaResponse);
        //log.debug("Request body for recaptcha: {}", body);
        ResponseEntity<Map> recaptchaResponseEntity =
                restTemplateBuilder.build()
                        .postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL+
                                        "?secret={secret}&response={response}",
                                body, Map.class, body);

        //log.debug("Response from recaptcha: {}",
//                recaptchaResponseEntity);
        Map<String, Object> responseBody = recaptchaResponseEntity.getBody();
        return (Boolean)responseBody.get("success");
    }
}
