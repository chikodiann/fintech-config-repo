package fintech.accountservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountResponse {
    private UUID id;
    private String accountNumber;
    private UUID customerId;
    private String accountType;
    private BigDecimal balance;
}
