package dev.ritobina.Splitwise.services.strategy;

import dev.ritobina.Splitwise.models.Expense;
import dev.ritobina.Splitwise.models.Group;
import dev.ritobina.Splitwise.models.Transaction;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settleUp(Group group);
}
