package fintech.customerservice.service.impl;

import fintech.customerservice.client.AccountServiceClient;
import fintech.customerservice.dto.OnboardingRequest;
import fintech.customerservice.event.CustomerOnboardedEvent;
import fintech.customerservice.exception.BvnVerificationException;
import fintech.customerservice.exception.CustomerNotFoundException;
import fintech.customerservice.exception.DuplicateCustomerException;
import fintech.customerservice.model.Customer;
import fintech.customerservice.repository.CustomerRepository;
import fintech.customerservice.service.BvnValidationService;
import fintech.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepo;
    private final BvnValidationService bvnService;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;
    private final AccountServiceClient accountServiceClient;


    @Override
    @Transactional
    public Customer onboardCustomer(OnboardingRequest request) {
        // Validate BVN first
        if (!bvnService.validateBvn(request.getBvn())) {
            throw new BvnVerificationException("BVN validation failed");
        }

        // Check for existing customer
        if (existsByBvnOrEmail(request.getBvn(), request.getEmail()).isPresent()) {
            throw new DuplicateCustomerException("Customer already exists");
        }

        Customer customer = buildCustomerEntity(request);
        Customer savedCustomer = customerRepo.save(customer);

        // Publish domain event
        eventPublisher.publishEvent(new CustomerOnboardedEvent(savedCustomer.getId()));

        // Call Account Service to create default account
        accountServiceClient.createDefaultAccount(savedCustomer.getId());

        return savedCustomer;

    }

    @Override
    public Customer getCustomerById(UUID customerId) {
        return customerRepo.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Override
    public Optional<Customer> existsByBvnOrEmail(String bvn, String email) {
        return customerRepo.existsByBvnOrEmail(bvn, email);
    }

    private Customer buildCustomerEntity(OnboardingRequest request) {
        return Customer.builder()
                .bvn(request.getBvn())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .verified(false)
                .build();
    }
}
