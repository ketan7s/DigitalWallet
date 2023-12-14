package com.digitalwallet.entity;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import com.digitalwallet.util.AccountNumberGenerator;

public class Account {
    private int accountNumber;
    private User accountUser;
    private BigDecimal accountBalance;
    private Set<Transaction> transactions;

    public Account(String name, BigDecimal balance) {
        this.accountNumber = AccountNumberGenerator.getAccountNumber();
        this.accountUser = new User(name);
        this.accountBalance = balance;
        this.transactions = new TreeSet<>((a,b) -> a.getTransactionDate().compareTo(b.getTransactionDate()));
    }

    @Override
    public String toString() {
        return "Account [accountNumber=" + accountNumber + ", name=" + this.accountUser.getName() + ", balance=" + accountBalance
                + ", tranactions=" + transactions + "]";
    }

    public int getAccountNumber() { return accountNumber; }

    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public User getAccountUser() { return accountUser; }

    public void setAccountUser(User accountUser) { this.accountUser = accountUser; }

    public BigDecimal getAccountBalance() { return accountBalance; }

    public void setAccountBalance(BigDecimal accountBalance) { this.accountBalance = accountBalance; }

    public Set<Transaction> getTransactions() { return transactions; }

    public void setTransactions(Set<Transaction> transactions) { this.transactions = transactions; }

}
