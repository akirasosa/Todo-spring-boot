package myapps.jwtapp.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secret")
public class SecretController {

    @RequestMapping
    public Greeting secret() {
        return new Greeting("Hello from secret zone.");
    }

    @Data
    @AllArgsConstructor
    private static class Greeting {
        private final String message;
    }
}

