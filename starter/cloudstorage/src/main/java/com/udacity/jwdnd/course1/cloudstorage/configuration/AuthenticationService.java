package com.udacity.jwdnd.course1.cloudstorage.configuration;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class AuthenticationService implements AuthenticationProvider {


    private final UsersMapper userMapper;
    private final HashService hashService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserModel user = userMapper.getUser(username);
        if (user != null) {
            String encodedSalt = user.getSalt();
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);
            if (user.getPassword().equals(hashedPassword)) {
                return new UsernamePasswordAuthenticationToken(username, password,new ArrayList<>());
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
