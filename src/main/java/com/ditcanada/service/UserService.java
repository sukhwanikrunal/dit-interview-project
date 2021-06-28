package com.ditcanada.service;

import com.ditcanada.model.User;

public interface UserService {

    boolean isVerifiedUser (String username, String password);

    User addUser(String firstname, String lastname, String username, String password);

}
