package fintech.dashboardservice.service.impl;

import fintech.dashboardservice.client.AccountServiceClient;
import fintech.dashboardservice.client.BillsServiceClient;
import fintech.dashboardservice.client.TransferServiceClient;
import fintech.dashboardservice.dto.DashboardResponse;
import fintech.dashboardservice.dto.RecentTransaction;
import fintech.dashboardservice.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final AccountServiceClient accountServiceClient;
    private final TransferServiceClient transferServiceClient;
    private final BillsServiceClient billsServiceClient;

    @Override
    public DashboardResponse getDashboard(UUID customerId) {
        DashboardResponse response = new DashboardResponse();
        response.setCustomerId(customerId);
        response.setAccounts(accountServiceClient.getAccounts(customerId));

        List<RecentTransaction> recent = new ArrayList<>();
        recent.addAll(transferServiceClient.getRecentTransfers(customerId));
        recent.addAll(billsServiceClient.getRecentBillPayments(customerId));

        response.setRecentTransactions(recent);
        return response;
    }
}
