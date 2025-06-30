package fintech.transferservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransferResponse {
    private UUID transferId;
    private UUID customerId;
    private String destinationBankCode;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private String reference;
    private LocalDateTime createdAt;
}
