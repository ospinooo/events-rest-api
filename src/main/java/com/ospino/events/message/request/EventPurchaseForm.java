package com.ospino.events.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class EventPurchaseForm {

    @NotBlank
    private Integer feeId;
    public Integer getFeeId() { return feeId; }
    public void setFeeId(Integer feeId) { this.feeId = feeId; }

    private List<Long> users;
    public List<Long> getUsers() { return users; }
    public void setUsers(List<Long> users) { this.users = users; }
}
