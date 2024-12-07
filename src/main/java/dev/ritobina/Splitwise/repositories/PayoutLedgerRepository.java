package dev.ritobina.Splitwise.repositories;

import dev.ritobina.Splitwise.models.PayoutLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayoutLedgerRepository extends JpaRepository<PayoutLedger,Integer> {
}
