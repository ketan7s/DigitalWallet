package com.digitalwallet.services;

import com.digitalwallet.dao.WalletDao;
import com.digitalwallet.entity.Account;
import com.digitalwallet.entity.Transaction;

import java.util.Date;
import java.math.BigDecimal;

public class WalletService {

    private final WalletDao dao;

    public WalletService() { dao = new WalletDao(); }

    public void createWallet(String name, BigDecimal balance) {
        Account account = new Account(name, balance);
        dao.getAccountMap().put(account.getAccountNumber(), account);
        System.out.println("Account created for user " + name + " with account number " + account.getAccountNumber());
    }

    public void transferAmount(int senderAccNum, int receiverAccNum, BigDecimal transactionAmount) {
        if (!validate(senderAccNum, receiverAccNum, transactionAmount)) { return ; }
        Transaction transaction = new Transaction(senderAccNum, receiverAccNum, transactionAmount, new Date());
        Account senderAccount = dao.getAccountMap().get(senderAccNum);
        Account receiverAccount = dao.getAccountMap().get(receiverAccNum);
        senderAccount.setAccountBalance(senderAccount.getAccountBalance().subtract(transactionAmount));
        receiverAccount.setAccountBalance(receiverAccount.getAccountBalance().add(transactionAmount));
        senderAccount.getTransactions().add(transaction);
        receiverAccount.getTransactions().add(transaction);
        System.out.printf("Transaction Successful");
    }

    private boolean validate(int senderAccNum, int receiverAccNum, BigDecimal transactionAmount) {

        final BigDecimal minTransactionAmount = BigDecimal.valueOf(0.0001);
        if (!dao.getAccountMap().containsKey(senderAccNum)) {
            System.out.println("Invalid Sender account number");
            return false;
        }
        if (!dao.getAccountMap().containsKey(receiverAccNum)) {
            System.out.println("Invalid Receiver account number");
            return false;
        }
        if (senderAccNum == receiverAccNum) {
            System.out.println("Sender and Receiver cannot be same.");
            return false;
        }
        if (transactionAmount.compareTo(minTransactionAmount) < 0) {
            System.out.println("Amount too low");
            return false;
        }
        if (transactionAmount.compareTo(dao.getAccountMap().get(senderAccNum).getAccountBalance()) > 0) {
            System.out.println("Insufficient account balance");
            return false;
        }
        return true;
    }

    public void statement(int accountNumber) {
        if (!dao.getAccountMap().containsKey(accountNumber)) {
            System.out.printf("Invalid Account number");
            return ;
        }
        Account account = dao.getAccountMap().get(accountNumber);
        System.out.println("Summary for account number: " + accountNumber);
        System.out.println("Current Balance: " + account.getAccountBalance());
        System.out.println("Your Transaction History");
        System.out.println(account.getTransactions());
    }

    public void overview() {
        for (int accNum: dao.getAccountMap().keySet()) {
            System.out.println("Current Balance for Account number " + accNum + ": ");
            System.out.println(dao.getAccountMap().get(accNum).getAccountBalance());
        }
    }

}
