package fintech.transferservice.controller;

import fintech.transferservice.dto.BankResponse;
import fintech.transferservice.dto.TransferRequest;
import fintech.transferservice.dto.TransferResponse;
import fintech.transferservice.service.BankService;
import fintech.transferservice.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final BankService bankService;
    private final TransferService transferService;

    @GetMapping("/banks")
    public ResponseEntity<List<BankResponse>> getBanks() {
        return ResponseEntity.ok(bankService.getBanks());
    }

    @PostMapping
    public ResponseEntity<TransferResponse> submitTransfer(@RequestBody TransferRequest request) {
        return ResponseEntity.ok(transferService.submitTransfer(request));
    }
}
