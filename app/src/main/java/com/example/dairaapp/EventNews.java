package com.example.dairaapp;

public class EventNews {
    private String adder_username;
    private String msg;
    private String sb_ev_id;
    private String sb_ev_name;

    public EventNews() {
    }

    public EventNews(String adder_username, String msg, String sb_ev_id, String sb_ev_name) {
        this.adder_username = adder_username;
        this.msg = msg;
        this.sb_ev_id = sb_ev_id;
        this.sb_ev_name = sb_ev_name;
    }

// Getter Methods

    public String getAdder_username() {
        return adder_username;
    }

    public String getMsg() {
        return msg;
    }

    public String getSb_ev_id() {
        return sb_ev_id;
    }

    public String getSb_ev_name() {
        return sb_ev_name;
    }

    // Setter Methods

    public void setAdder_username(String adder_username) {
        this.adder_username = adder_username;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSb_ev_id(String sb_ev_id) {
        this.sb_ev_id = sb_ev_id;
    }

    public void setSb_ev_name(String sb_ev_name) {
        this.sb_ev_name = sb_ev_name;
    }
}
