package com.ospino.events.message.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

    /* FIELDS */
    private String token;
    private String type = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor
     * @param token
     * @param username
     * @param authorities
     */
    public JwtResponse(String token, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.username = username;
        this.authorities = authorities;
    }

    /**
     * Get the token created
     * @return
     */
    public String getToken() {
        return token;
    }

    public void setToken(String accessToken2) {
        this.token = accessToken2;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
