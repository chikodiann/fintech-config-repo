package fintech.transferservice.service.impl;

import fintech.transferservice.dto.TransferRequest;
import fintech.transferservice.dto.TransferResponse;
import fintech.transferservice.model.Transfer;
import fintech.transferservice.repository.TransferRepository;
import fintech.transferservice.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    @Override
    public TransferResponse submitTransfer(TransferRequest request) {
        Transfer transfer = Transfer.builder()
                .customerId(request.getCustomerId())
                .destinationBankCode(request.getDestinationBankCode())
                .destinationAccountNumber(request.getDestinationAccountNumber())
                .amount(request.getAmount())
                .reference(generateReference())
                .build();

        Transfer saved = transferRepository.save(transfer);

        TransferResponse response = new TransferResponse();
        response.setTransferId(saved.getId());
        response.setCustomerId(saved.getCustomerId());
        response.setDestinationBankCode(saved.getDestinationBankCode());
        response.setDestinationAccountNumber(saved.getDestinationAccountNumber());
        response.setAmount(saved.getAmount());
        response.setReference(saved.getReference());
        response.setCreatedAt(saved.getCreatedAt());
        return response;
    }

    private String generateReference() {
        return "TX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
