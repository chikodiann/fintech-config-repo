package fintech.transferservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransferRequest {
    private UUID customerId;
    private String destinationBankCode;
    private String destinationAccountNumber;
    private BigDecimal amount;
}
