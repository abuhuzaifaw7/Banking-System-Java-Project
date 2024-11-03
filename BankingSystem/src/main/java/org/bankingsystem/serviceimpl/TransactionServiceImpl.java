package org.bankingsystem.serviceimpl;

import org.bankingsystem.dao.TransactionDao;
import org.bankingsystem.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

    TransactionDao transactionDao = new TransactionDao();

    @Override
    public long credit(int userId, long amount) {
        return transactionDao.credit(userId, amount);
    }

    @Override
    public long debit(int userId, long amount) {
        return transactionDao.debit(userId, amount);
    }

    @Override
    public long getCurrentBalance(int userId) {
        return transactionDao.getCurrentBalance(userId);
    }
}
