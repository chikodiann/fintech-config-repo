package fintech.billsservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BillPaymentResponse {
    private UUID paymentId;
    private UUID customerId;
    private String billerCode;
    private String category;
    private String reference;
    private LocalDateTime createdAt;
}
