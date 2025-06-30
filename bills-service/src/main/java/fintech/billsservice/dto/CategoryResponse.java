package fintech.billsservice.dto;

import fintech.billsservice.model.Biller;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private String category;
    private List<Biller> billers;
}
