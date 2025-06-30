package fintech.accountservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountRequest {
    private UUID customerId;
    private String accountType;
}
