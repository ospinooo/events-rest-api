package com.ospino.events.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private long id;

    // General
    private String name;
    private String description; // Markdown.
    private String color;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    /* Times
    private Time init_time;
    private Time end_time;
    private TimeZone timezone;


    // Location
    private Long latitude;
    private Long longitude;


    @OneToMany(mappedBy = "event")
    private List<Fee> fees;

    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;

    @ManyToMany(mappedBy = "events")
    private Set<User> users;


    // Contact Event Info
    private String email;
    private String telephone;


    // Extra
    // private Notification notification;
    // private Attachment attachment;
    */
}







