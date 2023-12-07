package com.ra.model.service.User;

import com.ra.model.entity.User;
import com.ra.model.service.IGenericService;

public interface UserService extends IGenericService<User,Integer> {
    User checkEmail(String email);
    User checkLogin(String email ,String password);
}
