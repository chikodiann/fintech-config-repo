package fintech.dashboardservice.client;

import fintech.dashboardservice.dto.AccountSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceClient {

    private final RestTemplate restTemplate;
    private final String accountServiceUrl;

    public AccountServiceClient(RestTemplate restTemplate,
                                @Value("${services.account-service.url}") String accountServiceUrl) {
        this.restTemplate = restTemplate;
        this.accountServiceUrl = accountServiceUrl;
    }

    public List<AccountSummary> getAccounts(UUID customerId) {
        String url = accountServiceUrl + "/api/v1/accounts/customer/" + customerId;
        AccountSummary[] accounts = restTemplate.getForObject(url, AccountSummary[].class);
        return Arrays.asList(accounts);
    }
}
