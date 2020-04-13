package com.ospino.events.model;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Fee fee;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

}
