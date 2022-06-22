package com.example.dairaapp;

public class User {
    private String email;
    private String name;
    private String password;
    private String role;
    private String username;


    // Getter Methods

    public User(String email, String name, String password, String role, String username) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.username = username;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    // Setter Methods

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
