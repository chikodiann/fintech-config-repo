package fintech.dashboardservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RecentTransaction {
    private UUID transactionId;
    private String type; // "TRANSFER" or "BILL"
    private BigDecimal amount;
    private String reference;
    private LocalDateTime createdAt;
}
