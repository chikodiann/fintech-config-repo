package fintech.billsservice.service;

import fintech.billsservice.dto.BillPaymentRequest;
import fintech.billsservice.dto.BillPaymentResponse;

public interface PaymentService {
    BillPaymentResponse makePayment(BillPaymentRequest request);
}
