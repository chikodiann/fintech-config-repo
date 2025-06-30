package fintech.apigateway.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/customer")
    public ResponseEntity<String> customerFallback() {
        return ResponseEntity.status(503).body("Customer service is unavailable. Try again later.");
    }
}