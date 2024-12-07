package dev.ritobina.Splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PayoutLedger extends BaseModel{
    @ManyToOne
    private User user;
    private double amountPaid;
    private double amountOwed;
    @ManyToOne
    private Expense expense;
}
