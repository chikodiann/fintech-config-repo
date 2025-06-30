package fintech.dashboardservice.controller;

import fintech.dashboardservice.dto.DashboardResponse;
import fintech.dashboardservice.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/{customerId}")
    public ResponseEntity<DashboardResponse> getDashboard(@PathVariable UUID customerId) {
        return ResponseEntity.ok(dashboardService.getDashboard(customerId));
    }
}
