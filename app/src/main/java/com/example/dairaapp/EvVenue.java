package com.example.dairaapp;

public class EvVenue {
    private String sb_id;
    private String sb_name;
    private String venue_id;
    private String venue_name;

    public EvVenue(String sb_id, String sb_name, String venue_id, String venue_name) {
        this.sb_id = sb_id;
        this.sb_name = sb_name;
        this.venue_id = venue_id;
        this.venue_name = venue_name;
    }
// Getter Methods

    public EvVenue() {
    }

    public String getSb_id() {
        return sb_id;
    }

    public String getSb_name() {
        return sb_name;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    // Setter Methods

    public void setSb_id(String sb_id) {
        this.sb_id = sb_id;
    }

    public void setSb_name(String sb_name) {
        this.sb_name = sb_name;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }
}
