package fintech.transferservice.client;

import fintech.transferservice.dto.BankResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaystackClient {

    private final RestTemplate restTemplate;

    @Value("${transfer.banks-api-url}")
    private String banksApiUrl;

    @Value("${transfer.api-key}")
    private String apiKey;

    public List<BankResponse> fetchBanks() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                banksApiUrl,
                HttpMethod.GET,
                entity,
                Map.class
        );

        List<Map<String, String>> data = (List<Map<String, String>>) response.getBody().get("data");

        return data.stream().map(entry -> {
            BankResponse bank = new BankResponse();
            bank.setName(entry.get("name"));
            bank.setCode(entry.get("code"));
            return bank;
        }).toList();
    }
}
