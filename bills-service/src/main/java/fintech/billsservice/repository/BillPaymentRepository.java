package fintech.billsservice.repository;

import fintech.billsservice.model.BillPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillPaymentRepository extends JpaRepository<BillPayment, UUID> {
}
