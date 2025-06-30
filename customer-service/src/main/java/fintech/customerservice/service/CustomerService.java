package fintech.customerservice.service;

import fintech.customerservice.dto.OnboardingRequest;
import fintech.customerservice.model.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Customer onboardCustomer(OnboardingRequest request);
    Customer getCustomerById(UUID customerId);
    Optional<Customer> existsByBvnOrEmail(String bvn, String email);
}
