package nus.cloud.laughelevator.imagedetection;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot! and this ms is for Receive Face Detection Images from UI and saves into cloud.";
    }
    
}
