package fintech.billsservice.service;

import fintech.billsservice.dto.CategoryResponse;

import java.util.List;

public interface BillerService {
    List<CategoryResponse> getAllCategoriesWithBillers();
}
