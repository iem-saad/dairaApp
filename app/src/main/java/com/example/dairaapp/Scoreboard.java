package com.example.dairaapp;

public class Scoreboard {
    private String event_id;
    private String event_name;
    private String oc_id;
    private String player1;
    private String player2;
    private String player3;


    // Getter Methods

    public String getEvent_id() {
        return event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getOc_id() {
        return oc_id;
    }

    public String getPlayer1() {
        return player1;
    }

    public Scoreboard() {
    }

    public String getPlayer2() {
        return player2;
    }

    public String getPlayer3() {
        return player3;
    }

    // Setter Methods

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setOc_id(String oc_id) {
        this.oc_id = oc_id;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
    }

    public Scoreboard(String event_id, String event_name, String oc_id, String player1, String player2, String player3) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.oc_id = oc_id;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
    }
}
