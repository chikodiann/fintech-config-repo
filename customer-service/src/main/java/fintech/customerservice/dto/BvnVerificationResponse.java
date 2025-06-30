package fintech.customerservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BvnVerificationResponse {
    private boolean valid;
    private String message;
    private String bvn;
    private String firstName;
    private String lastName;
}
