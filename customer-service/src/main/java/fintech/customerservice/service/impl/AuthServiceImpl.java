package fintech.customerservice.service.impl;

import fintech.customerservice.config.JwtTokenProvider;
import fintech.customerservice.dto.AuthResponse;
import fintech.customerservice.dto.LoginRequest;
import fintech.customerservice.model.Customer;
import fintech.customerservice.repository.CustomerRepository;
import fintech.customerservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public AuthResponse authenticate(@Valid LoginRequest request) {
        Customer customer = customerRepo.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), customer.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = tokenProvider.generateToken(customer);
        storeTokenInRedis(customer.getId(), token);

        return new AuthResponse(
                customer.getId(),
                customer.getEmail(),
                token,
                tokenProvider.getExpirationDate(token).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        );
    }

    @Override
    public void invalidateToken(String token) {
        String customerId = String.valueOf(tokenProvider.getCustomerIdFromToken(token));
        redisTemplate.delete("auth_tokens:" + customerId);
    }

    private void storeTokenInRedis(UUID customerId, String token) {
        redisTemplate.opsForValue().set(
                "auth_tokens:" + customerId,
                token,
                tokenProvider.getJwtExpirationInMinutes(),
                TimeUnit.MINUTES
        );
    }
}
