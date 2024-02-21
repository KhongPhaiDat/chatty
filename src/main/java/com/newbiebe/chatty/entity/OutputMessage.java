package com.newbiebe.chatty.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class OutputMessage {
    private String from;
    private String text;
    private String time;

    public OutputMessage(String from, String text, int unixTimestamp) {
        this.from = from;
        this.text = text;
        this.time = unixTimestamp2timedate(unixTimestamp);
    }

    private String unixTimestamp2timedate(int unixTimestamp) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTimestamp), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, HH:mm");
        String formattedDate = dateTime.format(formatter);
        return formattedDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

