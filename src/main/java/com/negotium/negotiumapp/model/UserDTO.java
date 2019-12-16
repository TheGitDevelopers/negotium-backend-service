package com.negotium.negotiumapp.model;

import com.negotium.negotiumapp.domain.UserDetails;

public class UserDTO {

    private String username;
    private String password;
    private String email;
    private UserDetails details;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
