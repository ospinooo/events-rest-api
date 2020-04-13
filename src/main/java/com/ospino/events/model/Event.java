package com.ospino.events.model;

import javax.persistence.*;
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

    // Times
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
}







