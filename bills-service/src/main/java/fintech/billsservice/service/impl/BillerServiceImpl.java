package fintech.billsservice.service.impl;

import fintech.billsservice.dto.CategoryResponse;
import fintech.billsservice.model.Biller;
import fintech.billsservice.service.BillerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BillerServiceImpl implements BillerService {

    private final List<Biller> billers = Arrays.asList(
            new Biller("MTN", "MTN-01", "AIRTIME"),
            new Biller("Airtel", "AIRTEL-01", "AIRTIME"),
            new Biller("IKEDC", "IKEDC-01", "ELECTRICITY"),
            new Biller("EKEDC", "EKEDC-01", "ELECTRICITY")
    );

    @Override
    public List<CategoryResponse> getAllCategoriesWithBillers() {
        Map<String, List<Biller>> grouped = billers.stream()
                .collect(Collectors.groupingBy(Biller::getCategory));

        return grouped.entrySet().stream().map(entry -> {
            CategoryResponse response = new CategoryResponse();
            response.setCategory(entry.getKey());
            response.setBillers(entry.getValue());
            return response;
        }).collect(Collectors.toList());
    }
}
