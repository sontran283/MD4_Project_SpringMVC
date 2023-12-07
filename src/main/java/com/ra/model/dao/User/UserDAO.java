package com.ra.model.dao.User;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.User;

public interface UserDAO extends IGenericDAO<User, Integer> {
    User checkEmail(String email);
    User checkLogin(String email ,String password);
}
