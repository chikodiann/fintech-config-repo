package fintech.dashboardservice.service;

import fintech.dashboardservice.dto.DashboardResponse;

import java.util.UUID;

public interface DashboardService {
    DashboardResponse getDashboard(UUID customerId);
}
