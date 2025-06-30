package fintech.customerservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountServiceClient {

    private final RestTemplate restTemplate;

    @Value("${services.account-service.url}")
    private String accountServiceUrl;

    /**
     * Creates a default account for the given customer by calling Account Service.
     *
     * @param customerId UUID of the new customer
     * @return true if the call was successful
     */
    public boolean createDefaultAccount(UUID customerId) {
        String url = accountServiceUrl + "/api/v1/accounts";

        CreateAccountRequest request = new CreateAccountRequest(customerId, "SAVINGS");

        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Successfully created default account for customer: {}", customerId);
                return true;
            } else {
                log.warn("Failed to create account for customer {} - Status: {}", customerId, response.getStatusCode());
                return false;
            }
        } catch (Exception ex) {
            log.error("Error creating default account for customer {}", customerId, ex);
            return false;
        }
    }

    /**
     * Simple DTO for the account creation request payload.
     */
    public record CreateAccountRequest(UUID customerId, String accountType) {}
}
