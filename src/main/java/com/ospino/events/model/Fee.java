package com.ospino.events.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Fee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    private Event event;

    private Integer price;
    private String title;

    @Column(name="description", columnDefinition="TEXT")
    private String description;

    @OneToMany(mappedBy = "fee", cascade = CascadeType.REMOVE)
    private List<Ticket> tickets;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public Integer getPrice() { return price; }

    public void setEvent(Event event) { this.event = event; }
    public Object getEventData() {
        return new Object(){
            long id = getEventId();
            String title = getEventTitle();
            Date date = getEventDate();

            public Date getDate() { return date; }
            public long getId() { return id; }
            public String getTitle() { return title; }
        };
    }

    public Long getEventId() { return this.event.getId(); }
    protected String getEventTitle() { return this.event.getTitle(); }
    protected Date getEventDate() { return this.event.getDate(); }

    /*public Event getEvent() { return event; }*/
}







