package org.bankingsystem.dao;

import org.bankingsystem.entity.User;

import javax.xml.stream.events.StartElement;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    private DbConnection dbConnection = new DbConnection();

    public User createUser(User user) {
        int userId = generateUserId();
        String createUserQuery = "INSERT INTO BANKUSER VALUES (" + userId + " , '" + user.getName() + "' , '" + user.getMobileNumber() + "', '" + user.getAddress() + "' )";
        String addZeroBalanceInBalanceTable = "INSERT INTO balance values ( " + userId + " , 0)";

        int isUserSaved = -1;
        int isBalanceZeroInserted = -1;
        Statement statement = dbConnection.getStatement();
        try {
            isUserSaved = statement.executeUpdate(createUserQuery);
            isBalanceZeroInserted = statement.executeUpdate(addZeroBalanceInBalanceTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isUserSaved == -1 || isBalanceZeroInserted == -1) {
            throw new RuntimeException("Error occurred while creating user");
        } else {
            return user;
        }

    }


    public List<User> getAllUser() {
        String getAllUserQuery = "select * from bankuser ";
        Statement statement = dbConnection.getStatement();
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(getAllUserQuery);
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String mobileNumber = resultSet.getString(3);
                String address = resultSet.getString(4);
                User user = new User(userId, name, mobileNumber, address);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    public User getUserById(int id) {
        String getUserByIdQuery = "select * from bankUser where userid = " + id;
        Statement statement = dbConnection.getStatement();
        ResultSet resultSet = null;
        User user = null;
        try {
            resultSet = statement.executeQuery(getUserByIdQuery);
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String mobileNumber = resultSet.getString(3);
                String address = resultSet.getString(4);
                user = new User(userId, username, mobileNumber, address);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public User updateUser(int id, User user) {
        String updateQuery = "UPDATE BANKUSER SET userid = " + user.getUserId() + " , name = '" + user.getName() + "' , mobilenumber = '" + user.getMobileNumber() + "' , address = '" + user.getAddress() + "' where id = " + id;
        Statement statement = dbConnection.getStatement();
        int isUpdated = -1;
        try {
            isUpdated = statement.executeUpdate(updateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isUpdated != 1) {
            throw new RuntimeException("Failed to update User ");
        }
        return user;
    }

    public boolean deleteUser(int id) {
        String deleteUserQuery = "delete from bankUser where userId = " + id;
        Statement statement = dbConnection.getStatement();
        int isSuccessfullyDeleted = 0;
        try {
            isSuccessfullyDeleted = statement.executeUpdate(deleteUserQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isSuccessfullyDeleted != 1) {
            throw new RuntimeException("Failed to delete the user for ID : " + id);
        }
        return true;
    }

    public int generateUserId() {
        String generateUserIdQuery = "select case when  max(userid ) + 1 is  null then 1 else max(userid ) + 1  end as col from bankuser";
        int userId = 0;
        ResultSet resultSet = null;
        Statement statement = dbConnection.getStatement();
        try {
            resultSet = statement.executeQuery(generateUserIdQuery);
            while (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userId == 0) {
            throw new RuntimeException("Error occurred while generating UserId");
        } else {
            return userId;
        }
    }
}
