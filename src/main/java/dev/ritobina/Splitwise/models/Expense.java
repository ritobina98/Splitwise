package dev.ritobina.Splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{
    private double amount;
    private String description;
    @ManyToOne
    private Group group;
    @OneToMany
    @JoinColumn(name="expense_id")
    private List<PayoutLedger> payoutLedgers;
}
