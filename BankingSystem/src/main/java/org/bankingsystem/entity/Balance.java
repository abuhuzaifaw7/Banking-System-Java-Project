package org.bankingsystem.entity;

public class Balance {
    private int userId;
    private long currentBalance;

    public Balance() {
    }

    public Balance(int userId, long currentBalance) {
        this.userId = userId;
        this.currentBalance = currentBalance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(long currentBalance) {
        this.currentBalance = currentBalance;
    }
}
