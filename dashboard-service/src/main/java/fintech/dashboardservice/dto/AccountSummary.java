package fintech.dashboardservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountSummary {
    private UUID accountId;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
}
