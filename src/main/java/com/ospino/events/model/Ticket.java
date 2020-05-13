package com.ospino.events.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(uniqueConstraints = @UniqueConstraint(columnNames={Ticket.USER_COLUMN_NAME, Ticket.FEE_COLUMN_NAME}))
@Entity
public class Ticket {

    public static final String USER_COLUMN_NAME = "user_id";
    public static final String FEE_COLUMN_NAME = "fee_id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name=FEE_COLUMN_NAME)
    private Fee fee;

    @ManyToOne
    @JoinColumn(name=USER_COLUMN_NAME)
    private User user;

    public Ticket() {}

    public Ticket(@NotBlank Fee fee, @NotBlank User user) {
        this.fee = fee;
        this.user = user;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Fee getFee() { return fee; }
    public void setFee(Fee fee) { this.fee = fee; }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
}
