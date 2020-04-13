package com.ospino.events.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue; // Auto-generated primary key
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient; // not saved in the DB

@Entity
public class Event {

    @Id
    @GeneratedValue
    private long id; // ID and it is Generated (Increasing)

    private String firstname;
    private String lastname;
    private String email;
    private String telephone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
