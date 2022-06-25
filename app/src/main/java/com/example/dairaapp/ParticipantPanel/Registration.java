package com.example.dairaapp.ParticipantPanel;

public class Registration {
    private String participant_id;
    private String participant_mail;
    private String sb_id;
    private String sb_name;


    // Getter Methods

    public Registration(String participant_id, String participant_mail, String sb_id, String sb_name) {
        this.participant_id = participant_id;
        this.participant_mail = participant_mail;
        this.sb_id = sb_id;
        this.sb_name = sb_name;
    }

    public Registration() {
    }

    public String getParticipant_id() {
        return participant_id;
    }

    public String getParticipant_mail() {
        return participant_mail;
    }

    public String getSb_id() {
        return sb_id;
    }

    public String getSb_name() {
        return sb_name;
    }

    // Setter Methods

    public void setParticipant_id(String participant_id) {
        this.participant_id = participant_id;
    }

    public void setParticipant_mail(String participant_mail) {
        this.participant_mail = participant_mail;
    }

    public void setSb_id(String sb_id) {
        this.sb_id = sb_id;
    }

    public void setSb_name(String sb_name) {
        this.sb_name = sb_name;
    }
}
