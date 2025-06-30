package fintech.dashboardservice.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class DashboardResponse {
    private UUID customerId;
    private List<AccountSummary> accounts;
    private List<RecentTransaction> recentTransactions;
}
