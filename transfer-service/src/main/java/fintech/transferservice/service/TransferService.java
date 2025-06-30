package fintech.transferservice.service;

import fintech.transferservice.dto.TransferRequest;
import fintech.transferservice.dto.TransferResponse;

public interface TransferService {
    TransferResponse submitTransfer(TransferRequest request);
}
