package fintech.customerservice.controller;

import fintech.customerservice.dto.AuthResponse;
import fintech.customerservice.dto.CustomerResponse;
import fintech.customerservice.dto.LoginRequest;
import fintech.customerservice.dto.OnboardingRequest;
import fintech.customerservice.model.Customer;
import fintech.customerservice.service.AuthService;
import fintech.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AuthService authService;

    @PostMapping("/onboard")
    public ResponseEntity<CustomerResponse> onboardCustomer(
            @Valid @RequestBody OnboardingRequest request) {
        Customer customer = customerService.onboardCustomer(request);
        return ResponseEntity.created(URI.create("/customers/" + customer.getId()))
                .body(CustomerResponse.from(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
