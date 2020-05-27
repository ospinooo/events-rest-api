package com.ospino.events.message.response;

public class ValidUserResponse {
    private Boolean username;
    private Boolean email;

    public ValidUserResponse (Boolean username, Boolean email) {
        this.username = username;
        this.email = email;
    }

    public Boolean getUsername() { return username; }

    public Boolean getEmail() { return email; }
}
