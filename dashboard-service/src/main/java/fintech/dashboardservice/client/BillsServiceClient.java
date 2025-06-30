package fintech.dashboardservice.client;

import fintech.dashboardservice.dto.RecentTransaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
public class BillsServiceClient {

    private final RestTemplate restTemplate;
    private final String billsServiceUrl;

    public BillsServiceClient(RestTemplate restTemplate,
                              @Value("${services.bills-service.url}") String billsServiceUrl) {
        this.restTemplate = restTemplate;
        this.billsServiceUrl = billsServiceUrl;
    }

    public List<RecentTransaction> getRecentBillPayments(UUID customerId) {
        String url = billsServiceUrl + "/api/v1/bills/recent/" + customerId;
        RecentTransaction[] payments = restTemplate.getForObject(url, RecentTransaction[].class);
        return List.of(payments);
    }
}
