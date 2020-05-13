package com.ospino.events.model;

import javax.persistence.*;
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
    public Long getEventId() { return this.event.getId(); }
    /*public Event getEvent() { return event; }*/
}







