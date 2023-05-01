package com.example.eventbox;

import java.util.Date;

public class EventModel {

    private int id;
    private String name;
    private String date;
    private String description;
    private String place;

    public EventModel(int id, String name, String date, String description, String place) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.place = place;
    }

    // toString

    @Override
    public String toString() {
        return "EventModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", place='" + place + '\'' +
                '}';
    }


    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}