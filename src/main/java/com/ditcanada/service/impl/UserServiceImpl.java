package com.ditcanada.service.impl;

import com.ditcanada.dao.UserDao;
import com.ditcanada.entity.UserEntity;
import com.ditcanada.model.User;
import com.ditcanada.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean isVerifiedUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password) != null;
    }

    @Override
    public User addUser(String firstname, String lastname, String username, String password) {
        return userDao.addUser(firstname, lastname, username, password);
    }

}
