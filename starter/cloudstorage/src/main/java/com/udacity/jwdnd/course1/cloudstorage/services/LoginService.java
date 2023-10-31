package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    private final UsersMapper userMapper;
    private final  HashService hashService;

    public LoginService(UsersMapper usersMapper, HashService hashService) {
        this.userMapper = usersMapper;
        this.hashService = hashService;
    }
}
