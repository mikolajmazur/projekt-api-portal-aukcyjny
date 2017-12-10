package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/api/test")
    public String test(){
        return "Hello cebullegro!";
    }
}
