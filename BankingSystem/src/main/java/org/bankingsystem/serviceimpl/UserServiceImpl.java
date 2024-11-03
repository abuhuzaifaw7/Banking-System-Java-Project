package org.bankingsystem.serviceimpl;

import org.bankingsystem.dao.UserDao;
import org.bankingsystem.entity.User;
import org.bankingsystem.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDao();

    @Override
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUser();

    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User updateUser(int userId, User user) {
        return userDao.updateUser(userId, user);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }
}
