package com.ospino.events.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Fee {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Event event;

    @OneToMany(mappedBy = "fee")
    private List<Ticket> tickets;

}







