package dev.ritobina.Splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExpenseCreationReqDTO {
    private double amount;
    private String description;
    private Integer groupId;
    private List<PayoutDTO> payoutDTOS;
}
