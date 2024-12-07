package dev.ritobina.Splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity (name = "Customer")
public class User extends BaseModel{
    private String username;
    private String password;
    private String email;
    @ManyToMany
    private List<Group> groups;
    @ManyToMany
    private List<Transaction> transactions;
    @ManyToMany
    private List<Expense> expenses;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
