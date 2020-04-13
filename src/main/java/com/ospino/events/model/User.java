package com.ospino.events.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    @ManyToMany
    @JoinTable(name = "user_event",
            joinColumns = { @JoinColumn(name = "fk_user") },
            inverseJoinColumns = { @JoinColumn(name = "fk_event") })
    private Set<Event> events;
}
