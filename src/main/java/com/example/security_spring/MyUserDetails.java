package com.example.security_spring;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.example.security_spring.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.username = user.getName();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));

    }

    public MyUserDetails(String s) {
        this.username = s;
    }

    public MyUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {

        return username;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return active;
    }

}
