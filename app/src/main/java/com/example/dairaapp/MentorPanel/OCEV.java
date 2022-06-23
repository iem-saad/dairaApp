package com.example.dairaapp.MentorPanel;

public class OCEV {
    private String oc_mail;
    private String oc_name;
    private String sb_id;
    private String sb_name;

    public OCEV(String oc_mail, String oc_name, String sb_id, String sb_name) {
        this.oc_mail = oc_mail;
        this.oc_name = oc_name;
        this.sb_id = sb_id;
        this.sb_name = sb_name;
    }

// Getter Methods

    public OCEV() {
    }

    public String getOc_mail() {
        return oc_mail;
    }

    public String getOc_name() {
        return oc_name;
    }

    public String getSb_id() {
        return sb_id;
    }

    public String getSb_name() {
        return sb_name;
    }

    // Setter Methods

    public void setOc_mail(String oc_mail) {
        this.oc_mail = oc_mail;
    }

    public void setOc_name(String oc_name) {
        this.oc_name = oc_name;
    }

    public void setSb_id(String sb_id) {
        this.sb_id = sb_id;
    }

    public void setSb_name(String sb_name) {
        this.sb_name = sb_name;
    }
}
