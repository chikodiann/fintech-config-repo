package fintech.accountservice.service.impl;

import fintech.accountservice.dto.AccountRequest;
import fintech.accountservice.dto.AccountResponse;
import fintech.accountservice.model.Account;
import fintech.accountservice.repository.AccountRepository;
import fintech.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Value("${account.number.prefix}")
    private String accountPrefix;

    @Value("${account.number.length}")
    private int accountNumberLength;

    @Override
    @Transactional
    public AccountResponse createAccount(AccountRequest request) {
        String accountNumber = generateAccountNumber();

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .customerId(request.getCustomerId())
                .accountType(request.getAccountType())
                .balance(BigDecimal.ZERO)
                .build();

        Account saved = accountRepository.save(account);
        return mapToResponse(saved);
    }

    @Override
    public List<AccountResponse> getAccountsByCustomerId(UUID customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private String generateAccountNumber() {
        String randomPart = UUID.randomUUID().toString()
                .replaceAll("-", "")
                .substring(0, accountNumberLength - accountPrefix.length());
        return accountPrefix + randomPart;
    }

    private AccountResponse mapToResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setId(account.getId());
        response.setAccountNumber(account.getAccountNumber());
        response.setCustomerId(account.getCustomerId());
        response.setAccountType(account.getAccountType());
        response.setBalance(account.getBalance());
        return response;
    }
}
