package com.example.security_spring.model;

public class AutheticationReq {
    private String username;
    private String password;

    public AutheticationReq() {

    }

    public AutheticationReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
