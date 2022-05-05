package com.company.message;

import java.util.Date;

public class Message {
    private String receiver;
    private String tittle;
    private String message;
    private boolean viewed;
    private Date date;
    private String sender;

    public Message(String receiver, String tittle, String message, boolean viewed, Date date, String sender) {
        this.receiver = receiver;
        this.tittle = tittle;
        this.message = message;
        this.viewed = viewed;
        this.date = date;
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
