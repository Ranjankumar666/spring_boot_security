package com.example.security_spring.model;

public class AuthenticationRes {
    private String jwt;

    public AuthenticationRes(String jwt) {
        this.jwt = jwt;
    }

    public AuthenticationRes() {
    }

    public String getJwt() {
        return this.jwt;
    }

}
