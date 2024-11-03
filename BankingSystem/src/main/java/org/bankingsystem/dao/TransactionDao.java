package org.bankingsystem.dao;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransactionDao {
    DbConnection dbConnection = new DbConnection();

    public long debit(int userId, long amount) {
        int transactionId = generateTransactionId();
        String isValidWithdrawl = "select case when currentbalance > " + amount + " then 1 else 0 end as col from balance b"; //  b is alias
        String insertIntoTransactionTable = "INSERT INTO TRANSACTION VALUES ( " + transactionId + " , " + userId + " , " + amount + " , 0 ) ";
        String updateLastBalance = "update balance set currentbalance = currentbalance - " + amount + " where userId = " + userId;
        String getFinalAmount = "select currentbalance from balance b";
        Statement statement = dbConnection.getStatement();
        ResultSet resultSet = null;
        ResultSet getFinalAmountResultSet = null;
        int checkValidWithdrawl = -1;
        int isTransactionInserted = -1;
        int isCurrentBalanceUpdated = -1;
        int finalBalance = 0;
        try {
            resultSet = statement.executeQuery(isValidWithdrawl);

            while (resultSet.next()) {
                checkValidWithdrawl = resultSet.getInt(1);
            }

            if (checkValidWithdrawl == 0) {
                throw new RuntimeException("Insufficient Balance !!");
            }

            isTransactionInserted = statement.executeUpdate(insertIntoTransactionTable);
            isCurrentBalanceUpdated = statement.executeUpdate(updateLastBalance);

            getFinalAmountResultSet = statement.executeQuery(getFinalAmount);
            while (getFinalAmountResultSet.next()) {
                finalBalance = getFinalAmountResultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalBalance;
    }

    public long credit(int userId, long amount) {
        int transactionId = generateTransactionId();
        String insertIntoTransactionTable = "INSERT INTO TRANSACTION VALUES ( " + transactionId + " , " + userId + " , 0 ," + amount + " )";
        String updateBalanceTable = "UPDATE BALANCE SET currentBalance = currentBalance + " + amount + " where userId = " + userId;
        String getFinalAmount = "select currentbalance from balance where userid = " + userId;
        Statement statement = dbConnection.getStatement();
        ResultSet resultSet = null;
        int isTransactionSaved = -1;
        int isBalanceUpdated = -1;
        int finalAmount = 0;
        try {
            isTransactionSaved = statement.executeUpdate(insertIntoTransactionTable);
            isBalanceUpdated = statement.executeUpdate(updateBalanceTable);
            resultSet = statement.executeQuery(getFinalAmount);
            while (resultSet.next()) {
                finalAmount = resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isTransactionSaved != 1 || isBalanceUpdated != 1) {
            throw new RuntimeException("Credit for amount " + amount + " Failed !! ");
        }

        return finalAmount;
    }


    public long getCurrentBalance(int userId) {
        String getCurrentBalanceQuery = "select currentbalance from balance b where userid  = " + userId;
        Statement statement = dbConnection.getStatement();
        ResultSet resultSet = null;
        int currentBalance = 0;
        try {
            resultSet = statement.executeQuery(getCurrentBalanceQuery);
            while (resultSet.next()) {
                currentBalance = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentBalance;
    }

    public int generateTransactionId() {
        String generateTransactionIdQuery = "select case when  max(transactionid ) + 1 is  null then 1 else max(userid ) + 1  end as col from transaction";
        int transactionId = 0;
        ResultSet resultSet = null;
        Statement statement = dbConnection.getStatement();
        try {
            resultSet = statement.executeQuery(generateTransactionIdQuery);
            while (resultSet.next()) {
                transactionId = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (transactionId == 0) {
            throw new RuntimeException("Error occurred while generating UserId");
        } else {
            return transactionId;
        }
    }
}
