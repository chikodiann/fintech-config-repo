package fintech.customerservice.dto;

import fintech.customerservice.model.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class CustomerResponse {

    // Getters
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean verified;

    // Constructor
    public CustomerResponse(UUID id, String email, String firstName, String lastName, boolean verified) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.verified = verified;
    }


    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getEmail(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.isVerified()
        );
    }
}
