package com.ospino.events.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    // General
    @NotNull
    private String title;
    @NotNull
    private String subtitle;
    @NotNull
    private String description; // Markdown.

    @Basic
    private Date date;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    @Size(min=1)
    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
    private List<Fee> fees;


    public Long getUserId() { return user.getId(); }
    public String getUsername() { return user.getUsername(); }
    /*public User getUser() { return user; }*/
    public void setUser(User user) { this.user = user; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public void setId(long id) { this.id = id; }
    public long getId() { return id; }

    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public List<Fee> getFees() { return fees; }
    public void setFees(List<Fee> fees) { this.fees = fees; }

    public void addFee(Fee fee) { this.fees.add(fee); }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }
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







