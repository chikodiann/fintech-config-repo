package fintech.transferservice.service.impl;

import fintech.transferservice.client.PaystackClient;
import fintech.transferservice.dto.BankResponse;
import fintech.transferservice.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final PaystackClient paystackClient;

    @Override
    public List<BankResponse> getBanks() {
        return paystackClient.fetchBanks();
    }
}
