package ecommerce_springbootbatch.springboot.testboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String testEndpoint() {
        return "This is a test endpoint!";
    }
}
