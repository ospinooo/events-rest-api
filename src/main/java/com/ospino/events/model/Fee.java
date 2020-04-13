package com.ospino.events.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fee {

    @Id
    @GeneratedValue
    private long id;
}
