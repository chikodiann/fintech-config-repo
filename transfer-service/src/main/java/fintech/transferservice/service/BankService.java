package fintech.transferservice.service;

import fintech.transferservice.dto.BankResponse;

import java.util.List;

public interface BankService {
    List<BankResponse> getBanks();
}
