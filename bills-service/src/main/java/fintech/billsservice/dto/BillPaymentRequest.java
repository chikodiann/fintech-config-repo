package fintech.billsservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class BillPaymentRequest {
    private UUID customerId;
    private String category;
    private String billerCode;
    private BigDecimal amount;
}
