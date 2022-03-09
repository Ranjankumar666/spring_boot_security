package com.example.security_spring;

import java.util.Optional;

import com.example.security_spring.model.User;
import com.example.security_spring.model.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepo.findByName(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found" +
                username));

        return user.map(MyUserDetails::new).get();
    }

}
