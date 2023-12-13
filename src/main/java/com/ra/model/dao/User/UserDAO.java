package com.ra.model.dao.User;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.Product;
import com.ra.model.entity.User;

import java.util.List;

public interface UserDAO extends IGenericDAO<User, Integer> {
    List<User> paginater(Integer noPage);
    Integer getTotalPage();
    User checkEmail(String email);
    User checkLogin(String email ,String password);
    Boolean checkValidateEmail(String email);
}
