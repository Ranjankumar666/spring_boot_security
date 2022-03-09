package com.example.security_spring.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    public Optional<User> findByName(String name);

}
