package com.example.dairaapp.MentorPanel;

public class SubEvent {
    private String description;
    private String event_id;
    private String event_name;
    private String name;
    private String url;


    // Getter Methods

    public String getDescription() {
        return description;
    }

    public SubEvent() {
    }

    public SubEvent(String description, String event_id, String event_name, String name, String url) {
        this.description = description;
        this.event_id = event_id;
        this.event_name = event_name;
        this.name = name;
        this.url = url;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    // Setter Methods

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
