package dev.ritobina.Splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Transaction extends BaseModel{
    public Transaction(){

    }

    public Transaction(double amount, List<User> parties, Group group) {
        this.amount = amount;
        this.parties = parties;
        this.group = group;
    }

    private double amount;
    @ManyToMany
    private List<User> parties; //[0 -> who has to Pay, borrower]  [1 -> who will get Paid, lendor]
    //users[0] will pay users[1] amount
    @ManyToOne
    private Group group;

    @Override
    public String toString() {
        String str = "Paid amount : " +amount
                +" by : "+parties.get(0).getUsername()
                +" to : "+parties.get(1).getUsername();
        return str;
    }
}
