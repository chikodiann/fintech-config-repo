package fintech.billsservice.controller;

import fintech.billsservice.dto.BillPaymentRequest;
import fintech.billsservice.dto.BillPaymentResponse;
import fintech.billsservice.dto.CategoryResponse;
import fintech.billsservice.service.BillerService;
import fintech.billsservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillerService billerService;
    private final PaymentService paymentService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return ResponseEntity.ok(billerService.getAllCategoriesWithBillers());
    }

    @PostMapping("/payment")
    public ResponseEntity<BillPaymentResponse> makePayment(@RequestBody BillPaymentRequest request) {
        return ResponseEntity.ok(paymentService.makePayment(request));
    }
}
