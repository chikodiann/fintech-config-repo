package fintech.customerservice.service;

import fintech.customerservice.dto.AuthResponse;
import fintech.customerservice.dto.LoginRequest;
import jakarta.validation.Valid;

public interface AuthService {
    AuthResponse authenticate(@Valid LoginRequest request);
    void invalidateToken(String token);
}