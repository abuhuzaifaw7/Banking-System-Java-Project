package org.bankingsystem.entity;

public class Transaction {
    private int transactionId;
    private int userId;
    private long debit;
    private long credit;


    public Transaction() {
    }

    public Transaction(int transactionId, int userId, long debit, long credit) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.debit = debit;
        this.credit = credit;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public long getDebit() {
        return debit;
    }

    public void setDebit(long debit) {
        this.debit = debit;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", userId=" + userId +
                ", debit=" + debit +
                ", credit=" + credit +
                '}';
    }
}
