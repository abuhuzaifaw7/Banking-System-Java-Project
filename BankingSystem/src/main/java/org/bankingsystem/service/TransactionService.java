package org.bankingsystem.service;

public interface TransactionService {

    public long credit(int userId, long amount);

    public long debit(int userId, long amount);

    public long getCurrentBalance(int userId);

}
