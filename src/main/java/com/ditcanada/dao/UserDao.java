package com.ditcanada.dao;

import com.ditcanada.entity.UserEntity;
import com.ditcanada.model.User;

public interface UserDao {

    UserEntity findByUsername (String username);

    UserEntity findByUsernameAndPassword (String username, String password);

    User addUser(String firstname, String lastname, String username, String password);


}
