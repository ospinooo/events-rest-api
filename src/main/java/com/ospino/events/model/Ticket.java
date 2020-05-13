package com.ospino.events.model;

import org.hibernate.annotations.Type;
import org.springframework.boot.context.properties.bind.DefaultValue;

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

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean confirmed;

    public Ticket() {}

    public Ticket(@NotBlank Fee fee, @NotBlank User user) {
        this.fee = fee;
        this.user = user;
    }

    /* public User getUser() { return user; } */
    public Long getUserId() { return user.getId(); }
    public void setUser(User user) { this.user = user; }

    public Fee getFee() { return fee; }
    public void setFee(Fee fee) { this.fee = fee; }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public void setConfirmed(Boolean confirmed) { this.confirmed = confirmed; }
    public boolean isConfirmed() { return confirmed; }
}
