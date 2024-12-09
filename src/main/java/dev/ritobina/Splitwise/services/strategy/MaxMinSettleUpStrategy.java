package dev.ritobina.Splitwise.services.strategy;

import dev.ritobina.Splitwise.models.*;

import java.util.*;

public class MaxMinSettleUpStrategy implements SettleUpStrategy{

    @Override
    public List<Transaction> settleUp(Group group) {
        List<Expense> expenses = group.getExpenses();
        List<Transaction> transactions = new ArrayList<>();

        Comparator<UserAmount>  minUserAmountComp = Comparator.comparingDouble(UserAmount::getAmount);
        Comparator<UserAmount>  maxUserAmountComp = Comparator.comparingDouble(UserAmount::getAmount).reversed();

        PriorityQueue<UserAmount> minHeap = new PriorityQueue<>(minUserAmountComp);
        PriorityQueue<UserAmount> maxHeap = new PriorityQueue<>(maxUserAmountComp);

        for(Map.Entry<User,Double> entry : getOutstandingBalances(expenses).entrySet()){
            if(entry.getValue() < 0){
                minHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            } else if(entry.getValue()>0){
                maxHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            }
            else {
                System.out.println("User is already settled , username "+entry.getKey().getUsername());
            }
        }

        while(!(minHeap.isEmpty() || maxHeap.isEmpty())){
            UserAmount maxLendor = maxHeap.poll();
            UserAmount maxBorrower = minHeap.poll();

            //Borrower will get cleared out
            if(maxLendor.getAmount() > Math.abs(maxBorrower.getAmount())){
                transactions.add(
                        new Transaction(
                                Math.abs(maxBorrower.getAmount()),
                                List.of(maxBorrower.getUser(), maxLendor.getUser()),
                                group)
                        );
                maxLendor.setAmount(maxLendor.getAmount() - Math.abs(maxBorrower.getAmount()));
                maxHeap.add(maxLendor);
            }
            //Lendor will get cleared out
            else if(maxLendor.getAmount() < Math.abs(maxBorrower.getAmount())){
                transactions.add(
                        new Transaction(
                                Math.abs(maxLendor.getAmount()),
                                List.of(maxBorrower.getUser(), maxLendor.getUser()),
                                group)
                );
                maxBorrower.setAmount(maxLendor.getAmount() - Math.abs(maxBorrower.getAmount()));
                minHeap.add(maxBorrower);
            }
            //both settled
            else{
                transactions.add(
                        new Transaction(
                                Math.abs(maxLendor.getAmount()),
                                List.of(maxBorrower.getUser(), maxLendor.getUser()),
                                group)
                );
            }
        }
        return transactions;
    }

    private HashMap<User, Double> getOutstandingBalances(List<Expense> expenses){
        HashMap<User, Double> totalOutstandingMap = new HashMap<>();
        for(Expense expense : expenses){
            for(PayoutLedger payoutLedger : expense.getPayoutLedgers()){
                User user = payoutLedger.getUser();
                if(totalOutstandingMap.containsKey(user)){
                    double currentBalance = totalOutstandingMap.get(user);
                    currentBalance += payoutLedger.getAmountPaid()- payoutLedger.getAmountOwed();
                    totalOutstandingMap.put(user,currentBalance);
                }else{
                    totalOutstandingMap.put(user, payoutLedger.getAmountPaid() - payoutLedger.getAmountOwed());
                }
            }
        }
        return totalOutstandingMap;
    }

    private static class UserAmount{
        User user;
        Double amount;
        public UserAmount(User user, Double amount){
            this.user = user;
            this.amount = amount;
        }

        public User getUser() {
            return user;
        }

        public Double getAmount() {
            return amount;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }
}
