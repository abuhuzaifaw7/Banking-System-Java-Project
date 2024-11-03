package org.bankingsystem;


import org.bankingsystem.entity.User;
import org.bankingsystem.serviceimpl.TransactionServiceImpl;
import org.bankingsystem.serviceimpl.UserServiceImpl;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        TransactionServiceImpl transactionService = new TransactionServiceImpl();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 1 to create New Account");
        System.out.println("Press ANY other Number explore other options");
        int choice = scanner.nextInt();

        if(choice == 1){
            Scanner newScanner = new Scanner(System.in);
            System.out.print("Enter Name : ");
            String name = newScanner.nextLine();
            System.out.print("Enter Mobile Number : ");
            String mobileNumber = newScanner.nextLine();
            System.out.print("Enter Address : ");
            String address = newScanner.nextLine();
            User user = new User(0, name, mobileNumber, address);
            userService.createUser(user);
            System.out.println("User : " + name + " created Successfully !");
        }

        System.out.print("Enter your UserId : ");
        int userId = scanner.nextInt();


        while (true) {
            System.out.println("Press 1 to Get All Accounts");
            System.out.println("Press 2 to Get Account By Id");
            System.out.println("Press 3 to Update Account");
            System.out.println("Press 4 to Delete Account");
            System.out.println("Press 5 to Debit");
            System.out.println("Press 6 to Credit");
            System.out.println("Press 7 to Check Balance");
            System.out.println("Press 8 to Exit ");
            choice = scanner.nextInt();
            if (choice == 1) {
                List<User> userList = userService.getAllUsers();
                for (User user : userList) {
                    System.out.println(user);
                }
            } else if (choice == 2) {
                System.out.println(userService.getUserById(userId));
            } else if (choice == 3) {
                System.out.print("Enter Name : ");
                String name = scanner.nextLine();
                System.out.print("Enter Mobile Number : ");
                String mobileNumber = scanner.nextLine();
                System.out.print("Enter Address : ");
                String address = scanner.nextLine();
                User user = new User(0, name, mobileNumber, address);
                userService.updateUser(userId, user);
            } else if (choice == 4) {
                System.out.println(userService.deleteUser(userId));
            } else if (choice == 5) {
                System.out.println("Enter Amount to Debit : ");
                int debitAmount = scanner.nextInt();
                System.out.println(transactionService.debit(userId, debitAmount));
            } else if (choice == 6) {
                System.out.println("Enter Amount to Debit : ");
                int creditAmount = scanner.nextInt();
                System.out.println(transactionService.credit(userId, creditAmount));
            } else if (choice == 7) {
                System.out.println("Current Balance is : " + transactionService.getCurrentBalance(userId));
            } else if (choice == 8) {
                System.out.println("Thanks for using our Application !! ");
                break;
            } else {
                System.out.println("Invalid Option !");
            }
        }


    }
}