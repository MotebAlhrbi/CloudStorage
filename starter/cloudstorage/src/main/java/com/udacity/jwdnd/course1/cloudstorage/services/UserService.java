package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
@Service

public class UserService {

    private final UsersMapper usersMapper;
    private final  HashService hashService;


    public boolean isUsernameAvailable(String username){ return usersMapper.getUser(username) == null;}

    public void createUser(UserModel user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt  = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword() ,  encodedSalt);
        UserModel newUser = new UserModel(null , user.getUsername(),  encodedSalt , hashedPassword , user.getFirstname() , user.getLastname());
        usersMapper.insert(newUser);
    }

    public UserModel getUser(String username){
        return usersMapper.getUser(username);
    }

    public Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        UserModel user = getUser(currentUsername);
        return user.getUserId();
    }

}
