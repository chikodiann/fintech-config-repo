package fintech.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthResponse {
    private UUID customerId;
    private String email;
    private String token;
    private LocalDateTime expiresAt;
}
