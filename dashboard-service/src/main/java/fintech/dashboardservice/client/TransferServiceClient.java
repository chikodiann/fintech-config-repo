package fintech.dashboardservice.client;

import fintech.dashboardservice.dto.RecentTransaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
public class TransferServiceClient {

    private final RestTemplate restTemplate;
    private final String transferServiceUrl;

    public TransferServiceClient(RestTemplate restTemplate,
                                 @Value("${services.transfer-service.url}") String transferServiceUrl) {
        this.restTemplate = restTemplate;
        this.transferServiceUrl = transferServiceUrl;
    }

    public List<RecentTransaction> getRecentTransfers(UUID customerId) {
        String url = transferServiceUrl + "/api/v1/transfers/recent/" + customerId;
        RecentTransaction[] transfers = restTemplate.getForObject(url, RecentTransaction[].class);
        return List.of(transfers);
    }
}
