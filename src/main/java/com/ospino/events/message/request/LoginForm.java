package com.ospino.events.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {

    /**
     * Username
     */
    @NotBlank
    @Size(min=3, max = 60)
    private String username;

    /**
     * Password
     */
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


    /* FUNCTIONS */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
