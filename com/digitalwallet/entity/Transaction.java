package com.digitalwallet.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Transaction {

    private int senderAccNum;
    private int receiverAccNum;
    private BigDecimal transactionAmount;
    private Date transactionDate;

    public Transaction(int senderAccNum, int receiverAccNum, BigDecimal transactionAmount, Date date) {
        this.senderAccNum = senderAccNum;
        this.receiverAccNum = receiverAccNum;
        this.transactionAmount = transactionAmount;
        this.transactionDate = date;
    }

    @Override
    public String toString() {
        return "Transaction [from=" + senderAccNum + ", to=" + receiverAccNum + ", amount=" + transactionAmount + ", " +
                "date=" + transactionDate + "]";
    }

    public int getSender() { return senderAccNum; }

    public void setSender(int senderAccNum) { this.senderAccNum = senderAccNum; }

    public int getReceiver() { return receiverAccNum; }

    public void setReceiver(int receiverAccNum) { this.receiverAccNum = receiverAccNum; }

    public BigDecimal getTransactionAmount() { return transactionAmount; }

    public void setTransactionAmount(BigDecimal transactionAmount) { this.transactionAmount = transactionAmount; }

    public Date getTransactionDate() { return transactionDate; }

    public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; }

}
