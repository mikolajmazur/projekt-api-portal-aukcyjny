package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.authorization;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/auth")
    public String test(){
        return "test";
    }
}
