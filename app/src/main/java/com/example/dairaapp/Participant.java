package com.example.dairaapp;

public class Participant {

    private String email;
    private String name;

    // Getter Methods

    public Participant(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Participant() {
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    // Setter Methods

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }


}
