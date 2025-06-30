package fintech.accountservice.service;

import fintech.accountservice.dto.AccountRequest;
import fintech.accountservice.dto.AccountResponse;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountResponse createAccount(AccountRequest request);
    List<AccountResponse> getAccountsByCustomerId(UUID customerId);
}
