package com.company.calendar;


import java.util.Date;

public class CalendarFormat {
    private Date date;
    private String event;
    private String streamer;

    public CalendarFormat(Date date, String event, String streamer) {
        this.date = date;
        this.event = event;
        this.streamer = streamer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getStreamer() {
        return streamer;
    }

    public void setStreamer(String streamer) {
        this.streamer = streamer;
    }
}
