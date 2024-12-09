package dev.ritobina.Splitwise.services;

import dev.ritobina.Splitwise.models.PayoutLedger;
import dev.ritobina.Splitwise.repositories.PayoutLedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayoutLedgerService {

    @Autowired
    private PayoutLedgerRepository payoutLedgerRepository;

    public PayoutLedger createPayoutLedger(PayoutLedger payoutLedger){
        return payoutLedgerRepository.save(payoutLedger);
    }
}
