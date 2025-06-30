package fintech.customerservice.service.impl;

import fintech.customerservice.dto.BvnVerificationResponse;
import fintech.customerservice.exception.BvnServiceException;
import fintech.customerservice.service.BvnValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BvnValidationServiceImpl implements BvnValidationService {

    private final RestTemplate restTemplate;
    private final Environment env;

    @Override
    public boolean validateBvn(String bvn) {
        String apiUrl = env.getProperty("nibss.bvn.verification.url");
        String apiKey = env.getProperty("nibss.api.key");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);

        try {
            ResponseEntity<BvnVerificationResponse> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(Map.of("bvn", bvn), headers),
                    BvnVerificationResponse.class
            );

            return response.getStatusCode() == HttpStatus.OK
                    && response.getBody() != null
                    && response.getBody().isValid();
        } catch (RestClientException e) {
            log.error("BVN verification failed for BVN: {}", bvn, e);
            throw new BvnServiceException("BVN verification service unavailable");
        }
    }
}