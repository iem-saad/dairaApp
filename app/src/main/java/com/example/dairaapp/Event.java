package com.example.dairaapp;

public class Event {
    private String description;
    private String mentor_id;
    private String name;

    public Event() {
    }

    public Event(String description, String mentor_id, String name) {
        this.description = description;
        this.mentor_id = mentor_id;
        this.name = name;
    }

// Getter Methods

    public String getDescription() {
        return description;
    }

    public String getMentor_id() {
        return mentor_id;
    }

    public String getName() {
        return name;
    }

    // Setter Methods

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMentor_id(String mentor_id) {
        this.mentor_id = mentor_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
