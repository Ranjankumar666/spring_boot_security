package com.example.security_spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "User", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "active")
    @ColumnDefault("true")
    private boolean active;
    @Column(name = "role", nullable = false)
    @ColumnDefault("ROLE_USER")
    private String role;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
