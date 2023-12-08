package com.ra.model.service.User;

import com.ra.model.entity.Category;
import com.ra.model.entity.User;
import com.ra.model.service.IGenericService;

import java.util.List;

public interface UserService extends IGenericService<User,Integer> {
    User checkEmail(String email);
    User checkLogin(String email ,String password);
    List<Category> paginater(Integer noPage);
    Integer getTotalPage();
}
