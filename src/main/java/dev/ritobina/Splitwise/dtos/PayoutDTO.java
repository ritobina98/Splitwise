package dev.ritobina.Splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayoutDTO {
    private Integer userId;
    private double paidAmount;
    private double owedAmount;
}
