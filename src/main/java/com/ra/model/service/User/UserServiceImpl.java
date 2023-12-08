package com.ra.model.service.User;

import com.ra.model.dao.Category.CategoryDAO;
import com.ra.model.dao.User.UserDAO;
import com.ra.model.entity.Category;
import com.ra.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public List<User> findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public List<User> sortByName() {
        return userDAO.sortByName();
    }

    @Override
    public User findById(Integer integer) {
        return userDAO.findById(integer);
    }

    @Override
    public boolean saveOrUpDate(User user) {
        return userDAO.saveOrUpDate(user);
    }

    @Override
    public void delete(Integer integer) {
        userDAO.delete(integer);
    }

    @Override
    public User checkEmail(String email) {
        return userDAO.checkEmail(email);
    }

    @Override
    public User checkLogin(String email, String password) {
        return userDAO.checkLogin(email, password);
    }

    @Override
    public List<Category> paginater(Integer noPage) {
        return null;
    }

    @Override
    public Integer getTotalPage() {
        return null;
    }
}
