package com.ospino.events.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class EventPurchaseForm {

    @NotBlank
    private Integer feeId;

    public Integer getFeeId() { return feeId; }
    public void setFeeId(Integer feeId) { this.feeId = feeId; }

    private List<Assistant> assistants;

    public List<Assistant> getAssistants() { return assistants; }
    public void setAssistants(List<Assistant> assistants) { this.assistants = assistants; }


    public static class Assistant {
        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
