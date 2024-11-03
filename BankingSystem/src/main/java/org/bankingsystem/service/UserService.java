package org.bankingsystem.service;

import org.bankingsystem.entity.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);

    public List<User> getAllUsers();

    public User getUserById(int id);

    public User updateUser(int userId, User user);

    public boolean deleteUser(int id);


}
