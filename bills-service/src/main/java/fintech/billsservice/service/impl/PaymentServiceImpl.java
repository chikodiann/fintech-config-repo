package fintech.billsservice.service.impl;

import fintech.billsservice.dto.BillPaymentRequest;
import fintech.billsservice.dto.BillPaymentResponse;
import fintech.billsservice.model.BillPayment;
import fintech.billsservice.repository.BillPaymentRepository;
import fintech.billsservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final BillPaymentRepository paymentRepository;

    @Override
    public BillPaymentResponse makePayment(BillPaymentRequest request) {
        // For now, no real external call—just save it

        BillPayment payment = BillPayment.builder()
                .customerId(request.getCustomerId())
                .category(request.getCategory())
                .billerCode(request.getBillerCode())
                .amount(request.getAmount())
                .reference(generateReference())
                .build();

        BillPayment saved = paymentRepository.save(payment);

        BillPaymentResponse response = new BillPaymentResponse();
        response.setPaymentId(saved.getId());
        response.setCustomerId(saved.getCustomerId());
        response.setBillerCode(saved.getBillerCode());
        response.setCategory(saved.getCategory());
        response.setReference(saved.getReference());
        response.setCreatedAt(saved.getCreatedAt());

        return response;
    }

    private String generateReference() {
        return "BILL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
