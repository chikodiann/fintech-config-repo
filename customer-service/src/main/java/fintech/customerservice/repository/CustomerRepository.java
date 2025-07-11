package fintech.customerservice.repository;

import fintech.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByBvn(String bvn);
    Optional<Customer> findByEmail(String email);

    Optional<Customer> existsByBvnOrEmail(String bvn, String email);
}
